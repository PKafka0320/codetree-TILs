import java.io.*;
import java.util.*;

class Person {
    String name;
    Person prev, next;

    public Person(String name) {
        this.name = name;
        this.prev = this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        int totalPerson = Integer.parseInt(token.nextToken());
        int totalLine = Integer.parseInt(token.nextToken());
        int totalMsg = Integer.parseInt(token.nextToken());

        HashMap<String, Person> people = new HashMap<>();
        Person[] firstPerson = new Person[totalLine + 1];

        token = new StringTokenizer(reader.readLine());
        Person lastAdded = null;
        int mod = totalPerson / totalLine;
        for (int number = 1; number <= totalPerson; number++) {
            String name = token.nextToken();
            Person newPerson = new Person(name);
            people.put(name, newPerson);

            if ((number - 1) % mod + 1 == 1) {
                firstPerson[number / mod + 1] = newPerson;
                lastAdded = newPerson;
            }
            else {
                lastAdded.next = newPerson;
                newPerson.prev = lastAdded;
                lastAdded = newPerson;
            }
        }

        for (int msgNumber = 1; msgNumber <= totalMsg; msgNumber++) {
            token = new StringTokenizer(reader.readLine());
            int msgType = Integer.parseInt(token.nextToken());

            if (msgType == 1) {
                String jumperName = token.nextToken();
                String victimName = token.nextToken();

                Person jumper = people.get(jumperName);
                Person victim = people.get(victimName);

                for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
                    if (firstPerson[lineNumber] != jumper) continue;
                    firstPerson[lineNumber] = jumper.next;
                    break;
                }
                for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
                    if (firstPerson[lineNumber] != victim) continue;
                    firstPerson[lineNumber] = jumper;
                    break;
                }

                if (jumper.next != null) jumper.next.prev = jumper.prev;
                if (jumper.prev != null) jumper.prev.next = jumper.next;
                if (victim.prev != null) victim.prev.next = jumper;
                jumper.prev = victim.prev;
                jumper.next = victim;
                victim.prev = jumper;
            }
            else if (msgType == 2) {
                String name = token.nextToken();

                Person leaver = people.get(name);

                for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
                    if (firstPerson[lineNumber] != leaver) continue;
                    firstPerson[lineNumber] = leaver.next;
                    break;
                }

                if (leaver.next != null) leaver.next.prev = leaver.prev;
                if (leaver.prev != null) leaver.prev.next = leaver.next;
            }
            else if (msgType == 3) {
                String jumperFromName = token.nextToken();
                String jumperToName = token.nextToken();
                String victimName = token.nextToken();

                Person jumperFrom = people.get(jumperFromName);
                Person jumperTo = people.get(jumperToName);
                Person victim = people.get(victimName);

                for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
                    if (firstPerson[lineNumber] != jumperFrom) continue;
                    firstPerson[lineNumber] = jumperTo.next;
                    break;
                }
                for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
                    if (firstPerson[lineNumber] != victim) continue;
                    firstPerson[lineNumber] = jumperFrom;
                    break;
                }

                if (jumperTo.next != null) jumperTo.next.prev = jumperFrom.prev;
                if (jumperFrom.prev != null) jumperFrom.prev.next = jumperTo.next;
                if (victim.prev != null) victim.prev.next = jumperFrom;
                jumperFrom.prev = victim.prev;
                jumperTo.next = victim;
                victim.prev = jumperTo;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int lineNumber = 1; lineNumber <= totalLine; lineNumber++) {
            Person currentPerson = firstPerson[lineNumber];

            if (currentPerson == null) {
                answer.append("-1\n");
                continue;
            }

            while (currentPerson != null) {
                answer.append(currentPerson.name).append(" ");
                currentPerson = currentPerson.next;
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}