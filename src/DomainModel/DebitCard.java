package DomainModel;

import java.util.Date;

public class DebitCard {
    private int codCard;
    private int CVV;
    private Date date;

    public DebitCard(int codCard, int CVV, Date date){
        this.codCard = codCard;
        this.CVV = CVV;
        this.date = date;
    }

    public int getCodCard() {
        return codCard;
    }

    public void setCodCard(int codCard) { //TODO change int codCard into sequence of casual number.
        this.codCard = codCard;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
