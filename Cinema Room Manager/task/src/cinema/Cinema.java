package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int nRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int nSeats = scanner.nextInt();

        String[][] cinema = new String[nRows][nSeats];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nSeats; j++) {
                cinema[i][j] = "S";
            }
        }

        accessMenu(cinema, nRows, nSeats);
    }

    public static void accessMenu(String[][] cinema, int nRows, int nSeats) {
        Scanner scanner = new Scanner(System.in);
        int nSoldTickets = 0;
        int currentIncome = 0;

        int task;

        do {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            task = scanner.nextInt();

            switch (task) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    currentIncome += buyTicket(cinema, nRows, nSeats);
                    nSoldTickets++;
                    break;
                case 3:
                    showStatistics(nRows, nSeats, nSoldTickets, currentIncome);
                    break;
                case 0:
                    break;
            }
        } while (task != 0);
    }

    public static void showSeats (String[][] cinema) {
        System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.printf(" %d", i);
        }
        System.out.print("\n");

        int i = 1;
        for(String[] row : cinema) {
            System.out.printf("%d", i);
            for (String seat : row) {
                System.out.printf(" %s", seat);
            }
            System.out.print("\n");
            i++;
        }
    }

    public static int buyTicket (String[][] cinema, int nRows, int nSeats) {
        Scanner scanner = new Scanner(System.in);

        int row;
        int seat;

        boolean boundsException;
        boolean takenSeatException = false;

        do {
            System.out.println("\nEnter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();

            boolean rowException = row < 1 || nRows < row;
            boolean seatException = seat < 1 || nSeats < seat;
            boundsException = rowException || seatException;

            if (!boundsException) {
                takenSeatException = "B".equals(cinema[row - 1][seat - 1]);
                if (takenSeatException) {
                    System.out.println("\nThat ticket has already been purchased!");
                }
            } else {
                System.out.println("\nWrong input!");
            }

        } while (boundsException || takenSeatException);


        int size = nRows * nSeats;
        int ticketPrice;

        if (size <= 60) {
            ticketPrice = 10;
        } else if (row <= nRows/2) {
            ticketPrice = 10;
        } else{
            ticketPrice = 8;
        }

        cinema[row-1][seat-1] = "B";

        System.out.printf("%nTicket price: $%d%n", ticketPrice);
        return ticketPrice;
    }

    public static void showStatistics (int nRows, int nSeats, int nSoldTickets, int currentIncome) {
        int size = nRows * nSeats;
        float percentage = (float) 100 * nSoldTickets / size;
        int totalIncome = calculateIncome(nRows, nSeats);

        System.out.printf("%nNumber of purchased tickets: %d", nSoldTickets);
        System.out.printf("%nPercentage: %.2f%%", percentage);
        System.out.printf("%nCurrent income: $%d", currentIncome);
        System.out.printf("%nTotal income: $%d%n", totalIncome);
    }

    public static int calculateIncome(int nRows, int nSeats) {
        int income;
        int size = nRows * nSeats;

        if (size <= 60) {
            income = 10 * size;
        } else {
            income = 8 * size + 2 * (nRows / 2) * nSeats;
        }

        return income;
    }

}