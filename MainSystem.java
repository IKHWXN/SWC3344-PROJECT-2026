import java.io.*;
import java.util.*;

public class MainSystem {

    static LinkedList<AnalystInfo> analystList = new LinkedList<>();

    static Queue<AnalystInfo> internalQueue = new LinkedList<>();
    static Queue<AnalystInfo> externalQueue = new LinkedList<>();
    static Queue<AnalystInfo> criticalQueue = new LinkedList<>();

    static Stack<AnalystInfo> resolvedStack = new Stack<>();

    public static void main(String[] args) {

        readFile();              
        assignQueues();          
        displayQueues();         
        processQueues();         
        displayResolvedStack();  
    }

    // ================= PHASE 1 =================
    public static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("cyber_incidents.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                
                if (data.length < 9) continue;

                String analystId = data[0];
                String name = data[1];
                String expertise = data[2];

                String incidentId = data[3];
                String type = data[4];
                int severity = Integer.parseInt(data[5]);
                String date = data[6];
                String time = data[7];
                double cost = Double.parseDouble(data[8]);

                IncidentInfo incident = new IncidentInfo(
                        incidentId, type, severity, date, time, cost
                );

                AnalystInfo analyst = findAnalyst(analystId);

                if (analyst == null) {
                    analyst = new AnalystInfo(analystId, name, expertise);
                    analystList.add(analyst);
                }

                analyst.addIncident(incident);
            }

            br.close();

            System.out.println("✔ File loaded successfully (" + analystList.size() + " analysts)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AnalystInfo findAnalyst(String id) {
        for (AnalystInfo a : analystList) {
            if (a.analystId.equals(id)) {
                return a;
            }
        }
        return null;
    }

    // ================= PHASE 2 =================
    public static void assignQueues() {
        int count = 0;

        for (AnalystInfo a : analystList) {

            if (a.incidents.size() <= 3) {
                if (count % 2 == 0) {
                    internalQueue.add(a);
                } else {
                    externalQueue.add(a);
                }
                count++;
            } else {
                criticalQueue.add(a);
            }
        }
    }

    public static void displayQueues() {
        System.out.println("\n=== INTERNAL QUEUE ===");
        displayQueue(internalQueue);

        System.out.println("\n=== EXTERNAL QUEUE ===");
        displayQueue(externalQueue);

        System.out.println("\n=== CRITICAL QUEUE ===");
        displayQueue(criticalQueue);
    }

    public static void displayQueue(Queue<AnalystInfo> queue) {
        if (queue.isEmpty()) {
            System.out.println("No data\n");
            return;
        }

        for (AnalystInfo a : queue) {
            a.display();
        }
    }

    // ================= PHASE 3 =================
    public static void processQueues() {

        while (!internalQueue.isEmpty() || 
               !externalQueue.isEmpty() || 
               !criticalQueue.isEmpty()) {

            processBatch(internalQueue);
            processBatch(externalQueue);
            processBatch(criticalQueue);
        }
    }

    public static void processBatch(Queue<AnalystInfo> queue) {
        int count = 0;

        while (!queue.isEmpty() && count < 5) {
            AnalystInfo a = queue.poll(); 
            resolvedStack.push(a);        
            count++;
        }
    }

    public static void displayResolvedStack() {
        System.out.println("\n=== RESOLVED STACK (LIFO) ===");

        if (resolvedStack.isEmpty()) {
            System.out.println("No resolved data");
            return;
        }

        while (!resolvedStack.isEmpty()) {
            AnalystInfo a = resolvedStack.pop();
            a.display();
        }
    }
}