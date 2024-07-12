package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class CourrierA {
	
	private int id;

    private String type;

    private Date date;

    private String objet;

    private String affectations;

    private String destinataires;

    private int num;

    private int numBO;

    private Date dateBO;

    private String origine;

    private String observation;

    private String pj;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public CourrierA(int id, String type, Date date, String objet, String affectations, String destinataires, int num, int numBO, Date dateBO, String origine, String observation, String pj, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.objet = objet;
        this.affectations = affectations;
        this.destinataires = destinataires;
        this.num = num;
        this.numBO = numBO;
        this.dateBO = dateBO;
        this.origine = origine;
        this.observation = observation;
        this.pj = pj;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CourrierA{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", objet='" + objet + '\'' +
                ", affectations='" + affectations + '\'' +
                ", destinataires='" + destinataires + '\'' +
                ", num=" + num +
                ", numBO=" + numBO +
                ", dateBO=" + dateBO +
                ", origine='" + origine + '\'' +
                ", observation='" + observation + '\'' +
                ", pj='" + pj + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getAffectations() {
        return affectations;
    }

    public void setAffectations(String affectations) {
        this.affectations = affectations;
    }

    public String getDestinataires() {
        return destinataires;
    }

    public void setDestinataires(String destinataires) {
        this.destinataires = destinataires;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNumBO() {
        return numBO;
    }

    public void setNumBO(int numBO) {
        this.numBO = numBO;
    }

    public Date getDateBO() {
        return dateBO;
    }

    public void setDateBO(Date dateBO) {
        this.dateBO = dateBO;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
