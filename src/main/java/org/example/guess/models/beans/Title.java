<<<<<<< HEAD:src/main/java/org/example/guess/models/dao/Title.java
package org.example.guess.models.dao;

import org.example.guess.connection.OmdbConnection;
import org.example.guess.tools.GameManager;

import java.io.IOException;

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
        if(meuTituloOmdb == null)
            this.error = "Error getting data";
    }

    public Title(boolean plotValidation ) throws IOException, InterruptedException{

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

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlot() {
        return this.plot;
    }

    public String getError(){
        return error;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbID(){
        return this.imdbID;
    }

    public void setImdbID(String id){
        this.imdbID = id;
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
=======
package org.example.guess.models.beans;

import org.example.guess.connection.OmdbConnection;
import org.example.guess.tools.GameManager;

import java.io.IOException;

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
        if(meuTituloOmdb == null)
            this.error = "Error getting data";
    }

    public Title(boolean plotValidation ) throws IOException, InterruptedException{

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

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlot() {
        return this.plot;
    }

    public String getError(){
        return error;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbID(){
        return this.imdbID;
    }

    public void setImdbID(String id){
        this.imdbID = id;
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
>>>>>>> 0dfe66d (Organizando Beans e DAOs):src/main/java/org/example/guess/models/beans/Title.java
