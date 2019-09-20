package dominio.clasesATestear;

import dominio.Prenda;
import dominio.enumsAMockear.Categoria;
import java.util.List;

public class PreferenciaCategoria {
    private int temperaturaInferior;

    private int temperaturaSuperior;

    private Categoria categoria;

    public PreferenciaCategoria(int temperaturaInferior, int temperaturaSuperior, Categoria categoria) {
        this.temperaturaInferior = temperaturaInferior;
        this.temperaturaSuperior = temperaturaSuperior;
        this.categoria = categoria;
    }

    public boolean cumple(List<Prenda> prendas, int temperaturaEvento) {
        double temperatura = temperatura(prendas, temperaturaEvento);
        return temperaturaInferior <= temperatura && temperatura <= temperaturaSuperior;
    }

    public double cercania(List<Prenda> prendas, int temperaturaEvento) {
        return  Math.abs((this.temperaturaInferior + this.temperaturaSuperior) / 2 - temperatura(prendas, temperaturaEvento));
    }

    public void ajustar(int margen) {
        this.temperaturaSuperior += margen;
        this.temperaturaInferior += margen;
    }

    protected double temperatura(List<Prenda> prendas, int temperaturaEvento) {
        return categoria.obtenerTemperatura(prendas) + temperaturaEvento;
    }

    public int getTemperaturaInferior() {
        return temperaturaInferior;
    }

    public int getTemperaturaSuperior() {
        return temperaturaSuperior;
    }

    public Categoria getCategoria() { return categoria; }
}
