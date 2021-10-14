//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.29 alle 04:38:50 PM EET 
//


package medication;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Medication" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="medId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                   &lt;element name="medName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="taken" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "patientId",
    "medication"
})
@XmlRootElement(name = "MedicationHistory")
public class MedicationHistory {

    protected long patientId;
    @XmlElement(name = "Medication")
    protected List<MedicationHistory.Medication> medication;

    /**
     * Recupera il valore della proprietà patientId.
     * 
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Imposta il valore della proprietà patientId.
     * 
     */
    public void setPatientId(long value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the medication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the medication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedicationHistory.Medication }
     * 
     * 
     */
    public List<MedicationHistory.Medication> getMedication() {
        if (medication == null) {
            medication = new ArrayList<MedicationHistory.Medication>();
        }
        return this.medication;
    }


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
     *         &lt;element name="medId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *         &lt;element name="medName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="taken" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        "medId",
        "medName",
        "date",
        "taken"
    })
    public static class Medication {

        protected long medId;
        @XmlElement(required = true)
        protected String medName;
        @XmlElement(required = true)
        protected String date;
        protected boolean taken;

        /**
         * Recupera il valore della proprietà medId.
         * 
         */
        public long getMedId() {
            return medId;
        }

        /**
         * Imposta il valore della proprietà medId.
         * 
         */
        public void setMedId(long value) {
            this.medId = value;
        }

        /**
         * Recupera il valore della proprietà medName.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMedName() {
            return medName;
        }

        /**
         * Imposta il valore della proprietà medName.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMedName(String value) {
            this.medName = value;
        }

        /**
         * Recupera il valore della proprietà date.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDate() {
            return date;
        }

        /**
         * Imposta il valore della proprietà date.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDate(String value) {
            this.date = value;
        }

        /**
         * Recupera il valore della proprietà taken.
         * 
         */
        public boolean isTaken() {
            return taken;
        }

        /**
         * Imposta il valore della proprietà taken.
         * 
         */
        public void setTaken(boolean value) {
            this.taken = value;
        }

    }

}
