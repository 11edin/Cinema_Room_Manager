package cinema;

import java.util.Scanner;

public class Cinema {
    
    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        
        char[][] cinema = new char[rows + 1][seats + 1];
        inizialisation (cinema, rows, seats);
        
        boolean work = true;
        int tickets = 0;
        int income = 0;
        int x = 0;
        int y = 0;
        
        while(work) {
            
            menu();
            
            switch (scanner.nextInt()) {
                case 1:
                    showSeats(cinema, rows, seats);
                    continue;
                case 2:
                    do {
                        System.out.println("\nEnter a row number:");
                        x = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        y = scanner.nextInt();
                        
                        if (x > rows || y > seats) {
                            System.out.println("Wrong input!");
                        } else if (cinema[x][y] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                            continue;
                        } else {
                            break;
                        }
                    } while (true);
                    
                    income += buy(rows, seats, x);
                    displayCinema(cinema, x, y, rows, seats);
                    tickets++;
                    continue;
                case 3:
                    statistics(tickets, rows, seats, income);
                    continue;
                case 0:
                    work = false;
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void inizialisation (char[][] cinema, int rows, int seats) {
        char a = '1';
        
        for (int i = 0; i < rows + 1; i++) {
            char b = '1';
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = ' ';
                } else if (i == 0) {
                    cinema[i][j] = b;
                    b++;
                } else if (j == 0) {
                    cinema[i][j] = a;
                    a++;
                } else {
                    cinema[i][j] = 'S';
                }
            }
        }
    }
    
    public static void menu(){
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    
    public static void showSeats(char[][] cinema, int rows, int seats) {
        System.out.println("Cinema:");
        
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static int buy(int rows, int seats, int x) {
        int price = 0;
        if (rows * seats < 60) {
            price = 10;
        } else if (rows - x > rows / 2) {
            price = 10;
        } else {
            price = 8;
        }
        System.out.println("\nTicket price: $" + price);
        
        return price;
    }
    
    public static void displayCinema(char[][] cinema, int x, int y, int rows, int seats) {
        
        cinema[x][y] = 'B';
        System.out.println("\nCinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void statistics(int tickets, int rows, int seats, int income) {
        System.out.println("Number of purchased tickets: " + tickets);
        
        double percent = 100.0 * tickets / (rows * seats);
        System.out.printf("Percentage: %.2f%%", percent);
        System.out.println("\nCurrent income: $" + income);
        
        int profit = 0;
        
        if (rows * seats < 60) {
            profit = 10 * rows * seats;
        } else if (rows % 2 != 0) {
            profit = seats * ((rows / 2) * 10 +  ((rows / 2 + 1) * 8));
        } else {
            profit = (rows / 2) * seats * (8 + 10);
        }
        System.out.println("Total income: $" + profit);
    }
}