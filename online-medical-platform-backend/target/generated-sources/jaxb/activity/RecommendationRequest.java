//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.29 alle 04:38:50 PM EET 
//


package activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="monitoredDataId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="recommendation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "monitoredDataId",
    "recommendation"
})
@XmlRootElement(name = "RecommendationRequest")
public class RecommendationRequest {

    protected long monitoredDataId;
    @XmlElement(required = true)
    protected String recommendation;

    /**
     * Recupera il valore della proprietà monitoredDataId.
     * 
     */
    public long getMonitoredDataId() {
        return monitoredDataId;
    }

    /**
     * Imposta il valore della proprietà monitoredDataId.
     * 
     */
    public void setMonitoredDataId(long value) {
        this.monitoredDataId = value;
    }

    /**
     * Recupera il valore della proprietà recommendation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * Imposta il valore della proprietà recommendation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendation(String value) {
        this.recommendation = value;
    }

}
