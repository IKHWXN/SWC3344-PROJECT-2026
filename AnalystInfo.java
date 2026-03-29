import java.util.*;

public class AnalystInfo {
    String analystId;
    String analystName;
    String expertiseArea;
    LinkedList<IncidentInfo> incidents;

    public AnalystInfo(String id, String name, String expertise) {
        this.analystId = id;
        this.analystName = name;
        this.expertiseArea = expertise;
        this.incidents = new LinkedList<>();
    }

    public void addIncident(IncidentInfo incident) {
        incidents.add(incident);
    }

    public double getTotalImpactCost() {
        double total = 0;
        for (IncidentInfo i : incidents) {
            total += i.impactCost;
        }
        return total;
    }

    public void display() {
        System.out.println("\n====================================");
        System.out.println("Analyst ID   : " + analystId);
        System.out.println("Name         : " + analystName);
        System.out.println("Expertise    : " + expertiseArea);
        System.out.println("Total Cases  : " + incidents.size());
        System.out.println("------------------------------------");
        System.out.println("Incidents:");

        for (IncidentInfo i : incidents) {
            i.display();
        }

        System.out.println("------------------------------------");
        System.out.println("Total Impact Cost: RM " + getTotalImpactCost());
        System.out.println("====================================");
    }
}