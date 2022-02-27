package com.keyin.Client;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/* class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter name, age and salary:");
        // String input
        String name = myObj.nextLine();
        // Numerical input
        int age = myObj.nextInt();
        double salary = myObj.nextDouble();
        // Output input by user
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }
} */


public class Client {
        public static void main(String[] args) {
            Boolean exit = false;

            // Create scanner
            Scanner scanner = new Scanner(System.in);

            int choiceInt = 0;
            while (!exit) {
                // Print menu
                System.out.println("1. Search Members");
                System.out.println("2. Search Tournaments");
                System.out.println("3. Exit");

                // Get input from user
                System.out.print("Enter your choice: ");
                choiceInt = scanner.nextInt();

                if (choiceInt == 1) {
                    System.out.println("------------------");
                    System.out.println("Search Member by: ");
                    System.out.println("1. Name");
                    System.out.println("2. Email");
                    System.out.println("3. Phone Number");

                    int choiceMemberInt = scanner.nextInt();

                    String queryString = null;

                    // CHOICE = NAME
                    if (choiceMemberInt == 1) {
                        // Get member first name
                        System.out.print("  Enter member first name: ");
                        String memberFirstName = scanner.next();

                        // Get member last name
                        System.out.print("  Enter member last name: ");
                        String memberLastName = scanner.next();

                        // Querystring
                        queryString = "name=" + memberFirstName + "%20" + memberLastName;
                    }

                    // CHOICE = EMAIL
                    if (choiceMemberInt == 2) {
                        // Get member email
                        System.out.print("  Enter member email: ");
                        String memberEmail = scanner.next();

                        // Querystring
                        queryString = "email=" + memberEmail;
                    }

                    // CHOICE = PHONE
                    if (choiceMemberInt == 3) {
                        // Get member email
                        System.out.print("  Enter member phone: ");
                        String memberPhone = scanner.next();

                        // Querystring
                        queryString = "phone=" + memberPhone;
                    }

                    // Create URI
                    URI uri = URI.create("http://localhost:8080/api/member/" + queryString);

                    // Get HTTP Response
                    UncheckedObjectMapper objectMapper = new UncheckedObjectMapper();

                    HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/member/" + queryString))
                            .header("Accept", "application/json")
                            .build();

                    CompletableFuture<Map<String, String>> response = HttpClient.newHttpClient()
                            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .thenApply(objectMapper::readValue);

                    ArrayList<String> responseList = new ArrayList<>(response.join().values());

                    System.out.println(responseList);

                    // Assign values to variables
                    String memberName = responseList.get(0);
                    String memberAddress = responseList.get(1);
                    String memberEmail = responseList.get(2);
                    String memberPhone = responseList.get(3);
                    String membershipStartDate = responseList.get(4);
                    Object membershipDuration = responseList.get(5);
                    String membershipType = responseList.get(6);
                    Object membershipFamilyMembers = responseList.get(7);
                    Object currentTournaments = responseList.get(8);
                    Object pastTournaments = responseList.get(9);
                    Object upcomingTournaments = responseList.get(10);

                    // Print values to the screen
                    System.out.println("Member Name:               " + memberName);
                    System.out.println("Member Address:            " + memberAddress);
                    System.out.println("Member Email:              " + memberEmail);
                    System.out.println("Member Phone:              " + memberPhone);
                    System.out.println("Membership Start:          " + membershipStartDate);
                    System.out.println("Membership Duraction:      " + membershipDuration);
                    System.out.println("Membership Type:           " + membershipType);
                    System.out.println("Membership Family Members: " + membershipFamilyMembers.toString().replace("[", "").replace("]", ""));
                    System.out.println("Current Tournaments:       " + currentTournaments.toString().replace("[", "").replace("]", ""));
                    System.out.println("Past Tournaments:          " + pastTournaments.toString().replace("[", "").replace("]", ""));
                    System.out.println("Upcoming Tournaments:      " + upcomingTournaments.toString().replace("[", "").replace("]", ""));
                }


                // TOURNAMENTS
                if (choiceInt == 2) {
                    System.out.println("------------------");
                    System.out.println("Search Tournament by: ");
                    System.out.println("1. Start Date: ");
                    System.out.println("2. End Date: ");
                    System.out.println("3. Location: ");

                    int choiceTournamentInt = scanner.nextInt();

                    String queryString = null;

                    // CHOICE = Start Date
                    if (choiceTournamentInt == 1) {
                        // Get tournament start date
                        System.out.print("  Enter start date for tournament: ");
                        String tournamentStartDate = scanner.next();

                        // Querystring
                        queryString = "startDate=" + tournamentStartDate;
                    }
                    // CHOICE = End Date
                    if (choiceTournamentInt == 2) {
                        // Get Tournament end date
                        System.out.print("  Enter end date for tournament: ");
                        String tournamentEndDate = scanner.next();

                        // Querystring
                        queryString = "endDate=" + tournamentEndDate;
                    }

                    // CHOICE = Location
                    if (choiceTournamentInt == 3) {
                        // Get tournament location
                        System.out.print("  Enter tournament location: ");
                        String tournamentLocation = scanner.next();

                        // Querystring
                        queryString = "location=" + tournamentLocation;
                    }

                    // Create URI
                    URI uri = URI.create("http://localhost:8080/api/tournament/" + queryString);

                    // Get HTTP Response
                    UncheckedObjectMapper objectMapper = new UncheckedObjectMapper();

                    HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/tournament/" + queryString))
                            .header("Accept", "application/json")
                            .build();

                    CompletableFuture<Map<String, String>> response = HttpClient.newHttpClient()
                            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .thenApply(objectMapper::readValue);

                    ArrayList<String> responseList = new ArrayList<>(response.join().values());

                    System.out.println(responseList);

                    // Assign values to variables
                    String startDate = responseList.get(0);
                    String endDate = responseList.get(1);
                    String location = responseList.get(2);
                    Object entryFee = responseList.get(3);
                    Object cashPrizeAmount = responseList.get(4);
                    Object participatingMembers = responseList.get(5);
                    Object finalStandings = responseList.get(6);

                    // Print values to the screen
                    System.out.println("Start Date Of Tournament: " + startDate);
                    System.out.println("End Date of Tournament:   " + endDate);
                    System.out.println("Location of Tournament:   " + location);
                    System.out.println("Entry Fee:                $" + entryFee.toString().replace("[", "").replace("]", ""));
                    System.out.println("Prize Amount:             $" + cashPrizeAmount.toString().replace("[", "").replace("]", ""));
                    System.out.println("Participating Members:    " + participatingMembers.toString().replace("[", "").replace("]", ""));
                    System.out.println("Final Standings:          " + finalStandings.toString().replace("[", "").replace("]", ""));
                }


                // EXIT
                if (choiceInt == 3) {
                    exit = true;
                }

                System.out.println("------------------");
            }
        }
}

class UncheckedObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
    /**
     * Parses the given JSON string into a Map.
     */
    Map<String, String> readValue(String content) {
        try {
            return this.readValue(content, new TypeReference<>() {
            });
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
}
