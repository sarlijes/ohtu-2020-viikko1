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
    Varasto kympinTaysiVarasto;
    Varasto virheelllinenVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        kympinTaysiVarasto = new Varasto(10, 10);
        virheelllinenVarasto = new Varasto(0.0);
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
    public void virheellinenVarastoLuodaanOikein() {
        assertEquals(0.0, virheelllinenVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, virheelllinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void taysiVarastoLuodaanOikein() {
        assertEquals(10.0, kympinTaysiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10.0, kympinTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellisetKahdenParametrinVarastotLuodaanOikein() {
        Varasto negatiivisetParametrit = new Varasto(-99,-3.56);
        assertEquals(0.0, negatiivisetParametrit.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, negatiivisetParametrit.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoSuurempiKuinTilavuus() {
        Varasto alkuSaldoSuurempiKuinTilavuus = new Varasto(12.0,773.56);
        assertEquals(12.0, alkuSaldoSuurempiKuinTilavuus.getTilavuus(), vertailuTarkkuus);
        assertEquals(12.0, alkuSaldoSuurempiKuinTilavuus.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaLisaysMeneeHukkaan() {
        Varasto ruokaHukka = new Varasto(100,90);
        ruokaHukka.lisaaVarastoon(12003256);
        assertEquals(100, ruokaHukka.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nollanLisaysEiMuutaSaldoa() {
        Varasto ruokaVarasto = new Varasto(100,90);
        ruokaVarasto.lisaaVarastoon(0);
        assertEquals(90, ruokaVarasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void eiVoiLisataNegatiivista() {
        Varasto ruokaVarasto = new Varasto(100,90);
        ruokaVarasto.lisaaVarastoon(-5588.3300);
        assertEquals(90, ruokaVarasto.getSaldo(), vertailuTarkkuus);
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
    public void nollanOttaminenEiVaikuta() {
        varasto.lisaaVarastoon(10);
        assertEquals(0, varasto.otaVarastosta(0), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void eiVoiOttaaNegatiivistaMaaraa() {
        assertEquals(0, varasto.otaVarastosta(-659.330), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(10);
        assertEquals(10, varasto.otaVarastosta(16500), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }


    @Test
    public void merkkijonoEsitysOikein() {
        Varasto ruokaVarasto = new Varasto(100,90);
        String odotettuMerkkijono = "saldo = 90.0, vielä tilaa 10.0";
        String ruokaVarastonKuvaus = ruokaVarasto.toString();
        assertEquals(odotettuMerkkijono, ruokaVarastonKuvaus);
    }

}