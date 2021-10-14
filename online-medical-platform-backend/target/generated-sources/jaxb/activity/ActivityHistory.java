//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.29 alle 04:38:50 PM EET 
//


package activity;

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
 *         &lt;element name="Activity" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="activityId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                   &lt;element name="activity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="isProblem" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="recommendation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "activity"
})
@XmlRootElement(name = "ActivityHistory")
public class ActivityHistory {

    protected long patientId;
    @XmlElement(name = "Activity")
    protected List<ActivityHistory.Activity> activity;

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
     * Gets the value of the activity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivityHistory.Activity }
     * 
     * 
     */
    public List<ActivityHistory.Activity> getActivity() {
        if (activity == null) {
            activity = new ArrayList<ActivityHistory.Activity>();
        }
        return this.activity;
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
     *         &lt;element name="activityId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *         &lt;element name="activity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="isProblem" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        "activityId",
        "activity",
        "startTime",
        "endTime",
        "isProblem",
        "recommendation"
    })
    public static class Activity {

        protected long activityId;
        @XmlElement(required = true)
        protected String activity;
        @XmlElement(required = true)
        protected String startTime;
        @XmlElement(required = true)
        protected String endTime;
        protected boolean isProblem;
        @XmlElement(required = true)
        protected String recommendation;

        /**
         * Recupera il valore della proprietà activityId.
         * 
         */
        public long getActivityId() {
            return activityId;
        }

        /**
         * Imposta il valore della proprietà activityId.
         * 
         */
        public void setActivityId(long value) {
            this.activityId = value;
        }

        /**
         * Recupera il valore della proprietà activity.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getActivity() {
            return activity;
        }

        /**
         * Imposta il valore della proprietà activity.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setActivity(String value) {
            this.activity = value;
        }

        /**
         * Recupera il valore della proprietà startTime.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStartTime() {
            return startTime;
        }

        /**
         * Imposta il valore della proprietà startTime.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStartTime(String value) {
            this.startTime = value;
        }

        /**
         * Recupera il valore della proprietà endTime.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEndTime() {
            return endTime;
        }

        /**
         * Imposta il valore della proprietà endTime.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEndTime(String value) {
            this.endTime = value;
        }

        /**
         * Recupera il valore della proprietà isProblem.
         * 
         */
        public boolean isIsProblem() {
            return isProblem;
        }

        /**
         * Imposta il valore della proprietà isProblem.
         * 
         */
        public void setIsProblem(boolean value) {
            this.isProblem = value;
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

}
