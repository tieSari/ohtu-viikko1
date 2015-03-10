package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
   
    @Test
    public void lisääLiikaaPalauttaaTilavuuden() {
        varasto.lisaaVarastoon(11);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisääNegatiivinenEiMuutaSaldoa() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(-1);
        assertEquals(varasto.getTilavuus(), tilavuus, vertailuTarkkuus);
    }
    
    @Test
    public void poistaNegatiivinenEiMuutaSaldoa() {
        double tilavuus = varasto.getTilavuus();
        varasto.otaVarastosta(-1);
        assertEquals(varasto.getTilavuus(), tilavuus, vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaaPalauttaaNolla() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(11);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    
    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}