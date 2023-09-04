package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание тестового набора перелетов
        List<Flight> flights = FlightBuilder.createFlights();

        // Создание экземпляра фильтра
        FlightFilter flightFilter = new FlightFilterImpl();


        List<Flight> filteredFlights1 = flightFilter.filterWhereDepartureBeforeCurrentTime(flights);
        System.out.println("\nПерелеты с вылетом не до текущего момента времени:");
        printFlights(filteredFlights1);

        List<Flight> filteredFlights2 = flightFilter.filterWhereArrivalBeforeDeparture(flights);
        System.out.println("\nПерелеты с сегментами, где прилет не раньше вылета:");
        printFlights(filteredFlights2);

        List<Flight> filteredFlights3 = flightFilter.filterWhereGroundTimeMoreThanTwoHours(flights);
        System.out.println("\nПерелеты с временем на земле не более 2 часов:");
        printFlights(filteredFlights3);
    }

    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
