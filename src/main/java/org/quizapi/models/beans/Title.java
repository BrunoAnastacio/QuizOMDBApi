package org.quizapi.models.beans;

import org.quizapi.connections.OmdbConnection;
import org.quizapi.util.GameManager;

public class Title {

    private String name;
    private String plot;
    private String imdbID;
    private String error;

    public Title(OmdbTitle meuTituloOmdb){
        this.name = meuTituloOmdb.Title();
        this.plot = meuTituloOmdb.Plot();
        this.imdbID = meuTituloOmdb.imdbID();
        this.error = meuTituloOmdb.Error();
//        if(meuTituloOmdb == null)
//            this.error = "Error getting data";
    }

    public Title(boolean plotValidation ) throws InterruptedException{

        if (plotValidation){
            do{
                OmdbConnection connection = new OmdbConnection();
                this.name = connection.searchTitlePerId().getName();
                this.plot = connection.searchTitlePerId().getPlot();
                if(this.plot == null) this.plot = "N/A";
                this.imdbID = connection.searchTitlePerId().getImdbID();
                this.error = connection.searchTitlePerId().getError();
                if(this.error == null) this.error = "No error";
                GameManager.setCountRequests();

            } while(isAInvalidPlot() || isAInvalidTitle());

        }else{
            do{
                OmdbConnection connection = new OmdbConnection();
                this.name = connection.searchTitlePerId().getName();
                this.plot = connection.searchTitlePerId().getPlot();
                if(this.plot == null) this.plot = "N/A";
                this.imdbID = connection.searchTitlePerId().getImdbID();
                this.error = connection.searchTitlePerId().getError();
                if(this.error == null) this.error = "No error";
                GameManager.setCountRequests();

            } while(isAInvalidTitle());
        }

        GameManager.addInCacheTitle(this.imdbID);
        System.out.println("Titulo criado");

    }

    public String getName() {
        return name;
    }

    public String getPlot() {
        return this.plot;
    }

    public String getError(){
        return error;
    }

    public String getImdbID(){
        return this.imdbID;
    }

    public boolean isAInvalidTitle() {
        return GameManager.containsInCache(this.imdbID) || this.error.contains("Error getting data.");
    }

    public boolean isAInvalidPlot(){
        return plot.contains("N/A");
    }

    @Override
    public String toString() {
        return "Title{" +
                "name='" + name + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }


}

