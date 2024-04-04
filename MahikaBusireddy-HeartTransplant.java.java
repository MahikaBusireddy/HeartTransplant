/**
 * 
 * HeartTransplant class
 * 
 * @author Ana Paula Centeno
 * @author Haolin (Daniel) Jin
 */
public class HeartTransplant {

    // patient array, each Patient is read from the data file
    private Patient[] patients;

    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge survivabilityByAge;

    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause survivabilityByCause;

    /*
     * Default constructor
     * Initializes patients to null.
     * Initializes survivabilityByAge to null.
     * Initializes survivabilityByCause to null. 
     */
    public HeartTransplant() {

        patients = null;
        survivabilityByAge = null;
        survivabilityByCause = null;
    }

    /*
     * Returns patients
     */
    public Patient[] getPatients() {
        return patients;
     } 

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    /*
     * 1) Initialize the instance variable patients array with numberOfLines length.
     * 
     * 2) Reads from the command line data file, use StdIn.readInt() to read an integer.
     *    File Format: 
     *      ID, ethnicity, Gender, Age, Cause, Urgency, State of health
     * 
     *    Each line refers to one Patient, all values are integers.
     * 
     */
    public void readPatients (int numberOfLines) {

        patients = new Patient[numberOfLines];

        for (int i = 0; i < numberOfLines; i++){
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
			int cause = StdIn.readInt();
			int urgency = StdIn.readInt();
			int stateOfHealth = StdIn.readInt();

            patients[i] = new Patient(id, ethnicity, gender, age,
             cause, urgency, stateOfHealth);
        }

    }

    /*
     * 1) Initialize the instance variable survivabilityByAge with a new survivabilityByAge object.
     * 
     * 2) Reads from the command line file to populate the object. 
     *    Use StdIn.readInt() to read an integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     * 
     */
    public void readSurvivabilityByAge (int numberOfLines) {
        survivabilityByAge = new SurvivabilityByAge();

        for (int i = 0; i < numberOfLines; i++){
            int age = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();

            survivabilityByAge.addData(age, yearsPostTransplant, rate);
        }

    }

    /*
     * 1) Initialize the instance variable survivabilityByCause with a new survivabilityByCause object.
     * 
     * 2) Reads from the command line file to populate the object. Use StdIn.readInt() to read an 
     *    integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     * 
     */
    public void readSurvivabilityByCause (int numberOfLines) {
        survivabilityByCause = new SurvivabilityByCause();

        for (int i = 0; i < numberOfLines; i++){
            int cause = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();

            survivabilityByCause.addData(cause, yearsPostTransplant, rate);
        }

    }
    
    /*
     * Returns a Patient array containing the patients, 
     * from the patients array, that have age above the parameter age.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with age above the parameter age.
     * 
     * Return null if there is no Patient with age above the 
     * parameter age.
     */ 
    public Patient[] getPatientsWithAgeAbove(int age) {
        int counter = 0;

        for (Patient ht : patients){ 
            if (ht.getAge() > age)
                counter++;
        }

        if (counter != 0){
            Patient[] patientsWithAgeAbove = new Patient[counter];
            int x = 0;

            for (int i = 0; i < patients.length; i++){
                if (patients[i].getAge() > age) 
                    patientsWithAgeAbove[x++] = patients[i];//
                
            }
            return patientsWithAgeAbove;
        }
  
        return null;
    }

    /*
     * Returns a Patient array containing the patients, from the patients array, 
     * that have the heart condition cause equal to the parameter cause.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the heart condition cause equal to the parameter cause.
     * 
     * Return null if there is no Patient with the heart condition cause 
     * equal to the parameter cause.
     */ 
    public Patient[] getPatientsByHeartConditionCause(int cause) {

        int count = 0;

        for (Patient ht: patients){ // try enhanced for loop -- might be on exam
            if (ht.getCause() == cause)
                count++;
        }

        if (count != 0){
            Patient[] patientsByHeartConditionCause = new Patient[count];
            int x = 0;

            for (int i = 0; i < patients.length; i++){
                if (patients[i].getCause() == cause)
                    patientsByHeartConditionCause[x++] = patients[i];
                
            }

            return patientsByHeartConditionCause;
        }

        return null;
    }

    /*
     * Returns a Patient array containing patients, from the patients array,
     * that have the state of health equal to the parameter state.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the state of health equal to the parameter state.
     * 
     * Return null if there is no Patient with the state of health 
     * equal to the parameter state.
     */ 
    public Patient[] getPatientsByUrgency(int urgency) {

        int count = 0;

        for (Patient p : patients){ //try using enhanced for loop
            if (p.getUrgency() == urgency)
                count++;            
        }

        if (count != 0){
            Patient[] patientsByUrgency = new Patient[count];
            int x = 0;

            for (int i = 0; i < patients.length; i++){
                if (patients[i].getUrgency() == urgency)
                    patientsByUrgency[x++] = patients[i];
            }
            return patientsByUrgency;
        }
	return null;
    }

    /*
     * Assume there is a heart available for transplantation surgery.
     * Also assume that the heart is of the same blood type as the
     * Patients on the patients array.
     * This method finds the Patient to be the recepient of this
     * heart.
     * 
     * The method returns a Patient from the patients array with
     * he highest potential for survivability after the transplant.
     * 
     * Assume the patient returned by this method will receive a heart,
     * therefore the Patient will no longer need a heart.
     * 
     * There is no correct solution, you may come up with any 
     * function to find the patient with the highest potential 
     * for survivability after the transplant.
     */ 
    public Patient getPatientForTransplant () {

	Patient[] array = getPatientsByUrgency(9);
    int counter = 0;

    for (int i = 0; i < array.length; i++){
        if (array[i].getNeedHeart() == true){
            int age = array[i].getAge();
            int cause = array[i].getCause();

            double survivabilityrate = (survivabilityByAge.getRate(age, 1) + 
                survivabilityByCause.getRate(cause, 1) / 2);
            double x = 0;

            if (survivabilityrate > x){
                counter = i;
                x = survivabilityrate;
            }
        }
    }
    //end of for loop
    array[counter].setNeedHeart(false);
    return array[counter];
    }
}