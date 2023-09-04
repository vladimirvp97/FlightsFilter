package com.gridnine.testing;

import java.util.List;

interface FlightFilter {
    List<Flight> filterWhereDepartureBeforeCurrentTime(List<Flight> flights);
    List<Flight> filterWhereArrivalBeforeDeparture(List<Flight> flights);
    List<Flight> filterWhereGroundTimeMoreThanTwoHours(List<Flight> flights);
}
