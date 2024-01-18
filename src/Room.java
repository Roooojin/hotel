public class Room {
    private int number;
    private int BedCount;
    private double reservationAmount;
    private boolean isReserved;

    public Room(int number, double reservationAmount) {
        this.number = number;
        this.BedCount=BedCount;
        this.reservationAmount = reservationAmount;
        this.isReserved = false;
    }
    public int getNumber() {
        return number;
    }

    public int getBedCount() {
        return BedCount;
    }

    public double getReservationAmount() {
        return reservationAmount;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserveRoom() {
        isReserved = true;
    }

    public void deliverRoom() {
        isReserved = false;
    }
}
