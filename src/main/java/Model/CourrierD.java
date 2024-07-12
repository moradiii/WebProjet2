package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class CourrierD {

	private int id;

    private String type;

    private Date date;

    private String objet;

    private String ampiliations;

    private String destinataires;

    private int num;

    private Date dateTrait;
    
    private String observation;

    private String pj;

    private Timestamp createdAt;
    
    private Timestamp updatedAt;

    public CourrierD() {
    }

    public CourrierD(int id, String type, Date date, String objet, String ampiliations, String destinataires, int num, Date dateTrait, String observation, String pj, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.objet = objet;
        this.ampiliations = ampiliations;
        this.destinataires = destinataires;
        this.num = num;
        this.dateTrait = dateTrait;
        this.observation = observation;
        this.pj = pj;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CourrierD{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", objet='" + objet + '\'' +
                ", ampiliations='" + ampiliations + '\'' +
                ", destinataires='" + destinataires + '\'' +
                ", num=" + num +
                ", dateTrait=" + dateTrait +
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

    public String getAmpiliations() {
        return ampiliations;
    }

    public void setAmpiliations(String ampiliations) {
        this.ampiliations = ampiliations;
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

    public Date getDateTrait() {
        return dateTrait;
    }

    public void setDateTrait(Date dateTrait) {
        this.dateTrait = dateTrait;
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
