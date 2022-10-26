package model.compute;

import model.data.Data;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class IrigoTest {
    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Irigo.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        Field Data = Data.class.getDeclaredField("instance");
        Data.setAccessible(true);
        Data.set(null, null);
    }

    @Test
    void trigger_tram_all_good() {
        Data data = mock(Data.class);
        when(Data.getInstance()).thenReturn()

        Data data = Data.getInstance();
        Irigo irigo = Irigo.getInstance();




    }
}