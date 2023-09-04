package com.gridnine.testing;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterImplTest {

    protected FlightFilter flightFilter;

    @BeforeEach
    void setUp() {
        flightFilter = new FlightFilterImpl();
    }

    @Test
    void testFilterWhereDepartureBeforeCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Segment> segments1 = createSegments(currentTime.minusHours(1), currentTime.plusHours(2));
        List<Segment> segments2 = createSegments(currentTime.plusHours(1), currentTime.plusHours(3));
        List<Segment> segments3 = createSegments(currentTime.minusHours(2), currentTime.minusHours(1));

        Flight flight1 = new Flight(segments1);
        Flight flight2 = new Flight(segments2);
        Flight flight3 = new Flight(segments3);

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        List<Flight> filteredFlights = flightFilter.filterWhereDepartureBeforeCurrentTime(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }

    @Test
    void testFilterWhereArrivalBeforeDeparture() {
        List<Segment> segments1 = createSegments(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        List<Segment> segments2 = createSegments(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(1));

        Flight flight1 = new Flight(segments1);
        Flight flight2 = new Flight(segments2);

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        List<Flight> filteredFlights = flightFilter.filterWhereArrivalBeforeDeparture(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }

    @Test
    void testFilterWhereGroundTimeMoreThanTwoHours() {
        var ldt = LocalDateTime.now();

        Flight flight1 = new Flight(createSegments(ldt, ldt.plusHours(2), ldt.plusHours(5), ldt.plusHours(10)));
        Flight flight2 = new Flight(createSegments(ldt, ldt.plusHours(2), ldt.plusHours(3), ldt.plusHours(5)));
        var flightList = new ArrayList<Flight>();
        flightList.add(flight1);
        flightList.add(flight2);

        List<Flight> filteredFlights = flightFilter.filterWhereGroundTimeMoreThanTwoHours(flightList);

        assertEquals(1, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }


    private List<Segment> createSegments(LocalDateTime... ldt) {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < ldt.length; i = i +2) {
            segments.add(new Segment(ldt[i], ldt[i+1]));
        }
        return segments;
    }
}
