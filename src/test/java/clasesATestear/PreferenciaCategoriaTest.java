package clasesATestear;

import clasesATestear.dummies.PrendaDummy;
import dominio.Prenda;
import dominio.clasesATestear.PreferenciaCategoria;
import dominio.enumsAMockear.Categoria;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PreferenciaCategoriaTest {
    private Categoria categoria;
    private PreferenciaCategoria preferenciaCategoria;
    private Prenda prenda;
    private List<Prenda> prendas;

    @Before
    public void setUp() {
        this.categoria = mock(Categoria.class);
        this.preferenciaCategoria = new PreferenciaCategoria( 0, 10, this.categoria);
        this.prenda = PrendaDummy.crearMock();
        this.prendas = new ArrayList<Prenda>() {{
            add(prenda);
        }};
        when(this.categoria.obtenerTemperatura(this.prendas)).thenReturn(5.0);
    }

    @Test
    public void cercania() {
        assertEquals(0.0, this.preferenciaCategoria.cercania(this.prendas, 0), 0);

        verify(this.categoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void cumple() {
        assertTrue(this.preferenciaCategoria.cumple(this.prendas, 0));

        verify(this.categoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void no_cumple() {
        assertFalse(this.preferenciaCategoria.cumple(this.prendas, 15));

        verify(this.categoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void ajustar_ambos_margenes_misma_temperatura() {
        this.preferenciaCategoria.ajustar(5);

        assertEquals(5, this.preferenciaCategoria.getTemperaturaInferior());
        assertEquals(15, this.preferenciaCategoria.getTemperaturaSuperior());
    }

}