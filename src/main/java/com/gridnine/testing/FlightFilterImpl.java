package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {

    @Override
    public List<Flight> filterWhereDepartureBeforeCurrentTime(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Flight> filterFlights = new ArrayList<>();

        here:
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(currentTime)) {
                    continue here;
                }
            }
            filterFlights.add(flight);
        }
        return filterFlights;
    }

    @Override
    public List<Flight> filterWhereArrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> filterFlights = new ArrayList<>();

        here:
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    continue here;
                }
            }
            filterFlights.add(flight);
        }
        return filterFlights;
    }

    @Override
    public List<Flight> filterWhereGroundTimeMoreThanTwoHours(List<Flight> flights) {
        List<Flight> filterFlights = new ArrayList<>();

        here:
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; ++i) {
                LocalDateTime currentArrival = segments.get(i).getArrivalDate();
                LocalDateTime nextDeparture = segments.get(i+1).getDepartureDate();
                if (currentArrival.plusHours(2).isBefore(nextDeparture)) continue here;
            }
            filterFlights.add(flight);
        }
        return filterFlights;
    }
}


