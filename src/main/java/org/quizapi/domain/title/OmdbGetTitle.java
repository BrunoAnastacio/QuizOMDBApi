package org.quizapi.domain.title;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.quizapi.util.exceptions.ProxyBlockException;
import org.quizapi.dto.OmdbGetTitleDto;
import org.quizapi.util.GameManager;
import org.quizapi.util.persistence.FakeJSON;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

public class OmdbGetTitle {
    private final String apiKey = System.getenv("API_KEY");
    private String url= "https://www.omdbapi.com/?apikey="+apiKey;
    private String json;
    private Title titulo;

    private void addJson() {
        Random r = new Random();
        List<String> fakeJson = FakeJSON.fill();
        json = fakeJson.get(r.nextInt(fakeJson.size()));
    }

    private void generateURL() {
        Random random = new Random();
        int ID = random.nextInt(1500000);
        url = url + "&i=tt"+String.format("%07d", ID);
    }

    private void HTTPCall() throws InterruptedException, ProxyBlockException{
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
            if (json.contains("html")){
                throw new ProxyBlockException();
            }

        }catch(IOException io){
            System.out.println(io.getMessage()+ " Erro de IO in HTTP Call");
            internalDataCall();
        }
    }

    private void deserialize(){
        try {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .setPrettyPrinting()
                    .create();
            OmdbGetTitleDto meuTituloOmdb = gson.fromJson(json, OmdbGetTitleDto.class);
            titulo = new Title(meuTituloOmdb);

        }catch(JsonSyntaxException | NullPointerException exception){
            System.out.println("[OmdbGetTitle]" + exception.getMessage());
        }
    }

    public Title searchTitlePerId(){
        return titulo;
    }


    public OmdbGetTitle() throws InterruptedException{
        try{
            externalDataCall();
        }catch (ProxyBlockException p){
            internalDataCall();
        } finally{
            deserialize();
        }
    }

    private void externalDataCall() throws ProxyBlockException, InterruptedException{
        generateURL();
        try{
            HTTPCall();
            
        }catch (Exception e){
            int i;
            for (i = 0; i<5;i++){
                HTTPCall();
            }
            System.out.println("Tentativa de contato com OMDB sem sucesso. Utilizando base de dados interna");
            internalDataCall();
        }
        GameManager.setStatus("Conexão com OMDB realizada.");
    }

    private void internalDataCall(){
        addJson();
        GameManager.setStatus("Sem conexão. Utilizando dados locais.");
    }
}
