package model_old.line;

import model_old.Station;
import model_old.tram.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Pos;
import utils.Shape;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LineTest {
    private static Tram tramActivated;

    @BeforeAll
    static void init(){
        tramActivated = spy(Tram.class);
        when(tramActivated.is_activated()).thenReturn(true);
    }



    @Test
    void activate() {
        //Nominal case:
        Exception exception =null;
        try {
            Station station1 = new Station(Shape.ROUND,new Pos(0,0));
            Station station2 = new Station(Shape.ROUND,new Pos(1,1));
            Tram tram = new Locomotive();
            Line line = new Oneway_line();
            line.activate(station1,station2,tram);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(exception,"Activation shouln't return exception if everything is ok");

        //Error cases:
        //If one of the input is null
        assertThrows(Exception.class,() -> {
            Line line = new Oneway_line();
            line.activate(null ,new Station(Shape.ROUND,new Pos(0,0)),new Locomotive());
            },"Activation should failed if station1 is null");
        assertThrows(Exception.class,() -> {
            Line line = new Oneway_line();
            line.activate(new Station(Shape.ROUND,new Pos(0,0)),null,new Locomotive());
            },"Activation should failed if station2 is null");
        assertThrows(Exception.class,() ->{
            Line line = new Oneway_line();
            line.activate(new Station(Shape.ROUND,new Pos(0,0)),new Station(Shape.ROUND,new Pos(0,0)),null);
            },"Activation should failed if tram is null");

        //If the locomotive is already on a line
        assertThrows(Exception.class,() -> {
            Line line = new Oneway_line();
            line.activate(new Station(Shape.ROUND,new Pos(0,0)),new Station(Shape.ROUND,new Pos(0,0)),tramActivated);
            },"Activation should failed if the locomotive is already on a line");

        //If the two station are the same
        assertThrows(Exception.class,() -> {
            Line line = new Oneway_line();
            Station station = new Station(Shape.ROUND,new Pos(0,0));
            line.activate(station,station,new Locomotive());
            },"Activation should failed if the locomotive is already on a line");
    }


}