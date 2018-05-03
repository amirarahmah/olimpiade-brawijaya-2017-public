package com.raioncommunity.android.ob2017.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.model.view.NavigationItem;
import com.raioncommunity.android.ob2017.model.view.OtherRenderableDrawable;
import com.raioncommunity.android.ob2017.model.view.PetunjukPenonton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradhawk on 8/24/2016.
 */

/*
* modified by arifinfirdaus
*/


public class DataList {


    public static Context context;

//    atletik, bulu tangkis, basket, bridge, catur, debat bing, desain poster, festival band,
//    fotografi, futsal, karate, komik, menyanyi dangdut, menyanyi pop, menyanyi seriosa,
//    news casting, paduan suara, panahan, pencak silat, renang, sinematografi, stand up comedy,
//    taekwondo, tenis lapangan, tenis meja, vocal grup, voli, cerpen, debat bind, balap sepeda,
//    melukis, puisi, sepak bola, kempo


    public static final CabangOlahraga[] cabangOlahragaList = {
            new CabangOlahraga(1, R.drawable.ic_action_atletik, R.string.caborAtletik),
            new CabangOlahraga(2, R.drawable.ic_action_bulu_tangkis, R.string.caborBuluTangkis),
            new CabangOlahraga(3, R.drawable.ic_action_basket, R.string.caborBasket),

            new CabangOlahraga(4, R.drawable.ic_action_bridge, R.string.caborBridge),
            new CabangOlahraga(5, R.drawable.ic_action_catur, R.string.caborCatur),
            new CabangOlahraga(6, R.drawable.ic_action_debat_indonesia, R.string.caborDebatIndonesia),

            new CabangOlahraga(7, R.drawable.ic_action_debat_inggris, R.string.caborDebatInggris),
            new CabangOlahraga(8, R.drawable.ic_action_desain_poster, R.string.caborDesainPoster),
            new CabangOlahraga(9, R.drawable.ic_action_festival_band, R.string.caborFestivalBand),

            new CabangOlahraga(10, R.drawable.ic_action_fotografi, R.string.caborFotografi),
            new CabangOlahraga(11, R.drawable.ic_action_futsal, R.string.caborFutsal),
            new CabangOlahraga(12, R.drawable.ic_action_karate, R.string.caborKarate),

            new CabangOlahraga(13, R.drawable.ic_action_kempo, R.string.caborKempo),
            new CabangOlahraga(14, R.drawable.ic_action_komik, R.string.caborKomik),
            new CabangOlahraga(15, R.drawable.ic_action_menembak, R.string.caborMenembak),

            new CabangOlahraga(16, R.drawable.ic_action_menyanyi_dangdut, R.string.caborMenyanyiDangdut),
            new CabangOlahraga(17, R.drawable.ic_action_menyanyi_keroncong, R.string.caborMenyanyiKeroncong),
            new CabangOlahraga(18, R.drawable.ic_action_menyanyi_pop, R.string.caborMenyanyiPop),

            new CabangOlahraga(19, R.drawable.ic_action_vocal_grup, R.string.caborPaduanSuara),
            new CabangOlahraga(20, R.drawable.ic_action_newscasting, R.string.caborNewscasting),
            new CabangOlahraga(21, R.drawable.ic_action_vocal_grup, R.string.caborPaduanSuara),

            new CabangOlahraga(22, R.drawable.ic_action_panahan, R.string.caborPanahan),
            new CabangOlahraga(23, R.drawable.ic_action_pencak_silat, R.string.caborPencakSilat),
            new CabangOlahraga(24, R.drawable.ic_action_pidato, R.string.caborPidato),

            new CabangOlahraga(25, R.drawable.ic_action_puisi, R.string.caborPuisi),
            new CabangOlahraga(26, R.drawable.ic_action_recycle, R.string.caborRecycle),
            new CabangOlahraga(27, R.drawable.ic_action_renang, R.string.caborRenang),

            new CabangOlahraga(28, R.drawable.ic_action_sinematografi, R.string.caborSinematografi),
            new CabangOlahraga(29, R.drawable.ic_action_monolog_stand_up_comedy, R.string.caborMonologStandUpComedy),
            new CabangOlahraga(30, R.drawable.ic_action_taekwondo, R.string.caborTaekwondo),

            new CabangOlahraga(31, R.drawable.ic_action_tennis_lapangan, R.string.caborTennisLapangan),
            new CabangOlahraga(32, R.drawable.ic_action_tennis_meja, R.string.caborTennisMeja),
            new CabangOlahraga(33, R.drawable.ic_action_vocal_grup, R.string.caborVocalGrup),

            new CabangOlahraga(34, R.drawable.ic_action_voli, R.string.caborVoli)
    };

    public static final Fakultas[] fakultasList = {
            new Fakultas(1, R.drawable.ic_fh, R.string.fakultasFH),
            new Fakultas(2, R.drawable.ic_feb, R.string.fakultasFEB),
            new Fakultas(3, R.drawable.ic_fia, R.string.fakultasFIA),
            new Fakultas(4, R.drawable.ic_fp, R.string.fakultasFP), // SOMETHING
            new Fakultas(5, R.drawable.ic_fapet, R.string.fakultasFapet),
            new Fakultas(6, R.drawable.ic_tekniik, R.string.fakultasFT),
            new Fakultas(7, R.drawable.ic_fk, R.string.fakultasFK),
            new Fakultas(8, R.drawable.ic_fpik, R.string.fakultasFPIK),
            new Fakultas(9, R.drawable.ic_fmipa, R.string.fakultasFMIPA),
            new Fakultas(10, R.drawable.ic_ftp, R.string.fakultasFTP),
            new Fakultas(11, R.drawable.ic_fisip, R.string.fakultasFISIP),
            new Fakultas(12, R.drawable.ic_fib, R.string.fakultasFIB),
            new Fakultas(13, R.drawable.ic_fkh, R.string.fakultasFKH),
            new Fakultas(14, R.drawable.ic_fkg, R.string.fakultasFKG),
            new Fakultas(15, R.drawable.ic_filkom3, R.string.fakultasFILKOM),
            new Fakultas(16, R.drawable.ic_vokasi2, R.string.fakultasVokasi),
            new Fakultas(17, R.drawable.ic_ub_kediri, R.string.fakultasKediri) // WRONG WITH THIS SHIT
    };

    /**
     * modified by arifinfirdaus
     */
    public static ArrayList<Fakultas> getFakultasArrayList() {
        ArrayList<Fakultas> fakultasArrayList = new ArrayList<>();
        fakultasArrayList.add(new Fakultas(1, R.drawable.ic_fh, R.string.fakultasFH));
        fakultasArrayList.add(new Fakultas(2, R.drawable.ic_feb, R.string.fakultasFEB));
        fakultasArrayList.add(new Fakultas(3, R.drawable.ic_fia, R.string.fakultasFIA));
        fakultasArrayList.add(new Fakultas(4, R.drawable.ic_fp, R.string.fakultasFP));
        fakultasArrayList.add(new Fakultas(5, R.drawable.ic_fapet, R.string.fakultasFapet));
        fakultasArrayList.add(new Fakultas(6, R.drawable.ic_tekniik, R.string.fakultasFT));
        fakultasArrayList.add(new Fakultas(7, R.drawable.ic_fk, R.string.fakultasFK));
        fakultasArrayList.add(new Fakultas(8, R.drawable.ic_fpik, R.string.fakultasFPIK));
        fakultasArrayList.add(new Fakultas(9, R.drawable.ic_fmipa, R.string.fakultasFMIPA));
        fakultasArrayList.add(new Fakultas(10, R.drawable.ic_ftp, R.string.fakultasFTP));
        fakultasArrayList.add(new Fakultas(11, R.drawable.ic_fisip, R.string.fakultasFISIP));
        fakultasArrayList.add(new Fakultas(12, R.drawable.ic_fib, R.string.fakultasFIB));
        fakultasArrayList.add(new Fakultas(13, R.drawable.ic_fkh, R.string.fakultasFKH));
        fakultasArrayList.add(new Fakultas(14, R.drawable.ic_fkg, R.string.fakultasFKG));
        fakultasArrayList.add(new Fakultas(15, R.drawable.ic_filkom3, R.string.fakultasFILKOM));
        fakultasArrayList.add(new Fakultas(16, R.drawable.ic_vokasi2, R.string.fakultasVokasi));
        fakultasArrayList.add(new Fakultas(17, R.drawable.ic_ub_kediri, R.string.fakultasKediri));
        return fakultasArrayList;
    }

    public static final NavigationItem[] navItemList = {
            new NavigationItem(0, R.drawable.ic_home, R.string.navDrawerBeranda),
            new NavigationItem(1, R.drawable.ic_action_jadwal, R.string.navDrawerJadwal),
            new NavigationItem(2, R.drawable.ic_action_tmedals_copy, R.string.navDrawerKlasemen),
            new NavigationItem(3, R.drawable.ic_action_fakultas_saya, R.string.navDrawerFakSaya),
            new NavigationItem(4, R.drawable.ic_action_fakultas_lain, R.string.navDrawerFakLain),
            new NavigationItem(5, R.drawable.ic_action_cabang_olim, R.string.navDrawerCabor),
            new NavigationItem(6, R.drawable.ic_action_news, R.string.navDrawerNews),
            new NavigationItem(7, R.drawable.ic_location_pointer, R.string.navDrawerGuide),
            new NavigationItem(8, R.drawable.ic_action_setting, R.string.navDrawerSetting),
            new NavigationItem(9, R.drawable.ic_action_spectator_guide, R.string.navDrawerAboutUs)
    };

    // ic_lokasi_lomba
    public static final OtherRenderableDrawable[] otherRenderableDrawables = {
            new OtherRenderableDrawable(R.drawable.ic_action_atletik),
            new OtherRenderableDrawable(R.drawable.ic_action_bulu_tangkis),
            new OtherRenderableDrawable(R.drawable.ic_action_basket),
            new OtherRenderableDrawable(R.drawable.ic_action_debat_inggris),
            new OtherRenderableDrawable(R.drawable.ic_action_tennis_lapangan),
            new OtherRenderableDrawable(R.drawable.ic_action_tennis_meja),
            new OtherRenderableDrawable(R.drawable.ic_action_voli),
            new OtherRenderableDrawable(R.drawable.ic_action_spectator_guide),
            new OtherRenderableDrawable(R.drawable.ic_share),
            new OtherRenderableDrawable(R.drawable.ic_lokasi_lomba),
            new OtherRenderableDrawable(R.drawable.ic_direction),
            new OtherRenderableDrawable(R.drawable.ic_action_kempo),
            new OtherRenderableDrawable(R.drawable.ic_action_sinematografi)
    };

    // Deprecated. Use getPetunjukPenontonArrayListList method instead.
    public static final PetunjukPenonton[] petunjukPenontonList = {
            new PetunjukPenonton(1, R.string.venueGajayana, R.drawable.gajayana, new int[]{1, 27}, -7.976156, 112.624374),
            new PetunjukPenonton(2, R.string.venueGazeboPerpus, R.drawable.gazebo_perpus, new int[]{8, 14, 10}, -7.953041, 112.613837),
            new PetunjukPenonton(3, R.string.venueGazeboUB, R.drawable.gazebo_ub, new int[]{9}, -7.955715, 112.612883),
            new PetunjukPenonton(4, R.string.venueGorPertamina, R.drawable.gor, new int[]{3, 11}, -7.953503, 112.616319),
            new PetunjukPenonton(5, R.string.venueLapBulutangkis, R.drawable.bulutangkis, new int[]{2}, -7.954671, 112.618540),
            new PetunjukPenonton(6, R.string.venueLapTenis, R.drawable.tenis, new int[]{12, 13, 23, 30, 31}, -7.954671, 112.618540),
            new PetunjukPenonton(7, R.string.venueLapTenisMeja, R.drawable.tempat_gkm, new int[]{4, 32}, -7.954671, 112.618540),
            //new PetunjukPenonton(8, R.string.venuePasarBurung, new int[]{10}, 0, 0),
            new PetunjukPenonton(9, R.string.venueSMA7, R.drawable.sman7, new int[]{22}, -7.9451531, 112.6284484),
            new PetunjukPenonton(10, R.string.venueUBSC, R.drawable.ubsc, new int[]{10}, -7.954671, 112.618540),
            new PetunjukPenonton(11, R.string.venueUBTV, R.drawable.ubtv, new int[]{16, 18, 19, 20, 21, 28, 29, 33}, -7.952281, 112.612732),
            new PetunjukPenonton(12, R.string.venueWidyaloka, R.drawable.widlok, new int[]{7}, -7.951675, 112.613640),
            new PetunjukPenonton(13, R.string.venueVoli, R.drawable.voli, new int[]{34}, -7.953957, 112.6158048)
    };

    // MARK: - Data untuk di petunjuk penonton OB 2017
    public static final ArrayList<PetunjukPenonton> getPetunjukPenontonArrayListList() {

        ArrayList<CabangOlahraga> caborListppUSBC = new ArrayList<>();
        caborListppUSBC.add(new CabangOlahraga(13, R.drawable.ic_action_kempo, R.string.caborKempo));
        caborListppUSBC.add(new CabangOlahraga(32, R.drawable.ic_action_tennis_meja, R.string.caborTennisMeja));
        caborListppUSBC.add(new CabangOlahraga(4, R.drawable.ic_action_bridge, R.string.caborBridge));
        caborListppUSBC.add(new CabangOlahraga(30, R.drawable.ic_action_taekwondo, R.string.caborTaekwondo));
        caborListppUSBC.add(new CabangOlahraga(31, R.drawable.ic_action_tennis_lapangan, R.string.caborTennisLapangan));
        caborListppUSBC.add(new CabangOlahraga(12, R.drawable.ic_action_karate, R.string.caborKarate));

        ArrayList<CabangOlahraga> caborListppGKM = new ArrayList<>();
        caborListppGKM.add(new CabangOlahraga(5, R.drawable.ic_action_catur, R.string.caborCatur));
        caborListppGKM.add(new CabangOlahraga(25, R.drawable.ic_action_puisi, R.string.caborPuisi)); // kok kembar?
        caborListppGKM.add(new CabangOlahraga(14, R.drawable.ic_action_komik, R.string.caborKomik));
        caborListppGKM.add(new CabangOlahraga(0, R.drawable.ic_action_help, R.string.lukis_ada_nggak_sih)); // di ... word ada lukis?


        ArrayList<CabangOlahraga> caborListppWidyaloka = new ArrayList<>();
        caborListppWidyaloka.add(new CabangOlahraga(25, R.drawable.ic_action_puisi, R.string.caborPuisi)); // kok kembar?
        caborListppWidyaloka.add(new CabangOlahraga(0, R.drawable.ic_action_help, R.string.cerpen_ada_nggk_sih));// di ... word ada cerpen?
        caborListppWidyaloka.add(new CabangOlahraga(19, R.drawable.ic_action_vocal_grup, R.string.caborPaduanSuara));
        caborListppWidyaloka.add(new CabangOlahraga(6, R.drawable.ic_action_debat_indonesia, R.string.caborDebatIndonesia));
        caborListppWidyaloka.add(new CabangOlahraga(7, R.drawable.ic_action_debat_inggris, R.string.caborDebatInggris));

        ArrayList<CabangOlahraga> caborListLapSampingGorPertamina = new ArrayList<>();
        caborListLapSampingGorPertamina.add(new CabangOlahraga(34, R.drawable.ic_action_voli, R.string.caborVoli));

        ArrayList<CabangOlahraga> caborListStasiunMalang = new ArrayList<>();
        caborListStasiunMalang.add(new CabangOlahraga(10, R.drawable.ic_action_fotografi, R.string.caborFotografi));

        ArrayList<CabangOlahraga> caborListppUBTV = new ArrayList<>();
        caborListppUBTV.add(new CabangOlahraga(29, R.drawable.ic_action_monolog_stand_up_comedy, R.string.caborMonologStandUpComedy));
        caborListppUBTV.add(new CabangOlahraga(20, R.drawable.ic_action_newscasting, R.string.caborNewscasting));
        caborListppUBTV.add(new CabangOlahraga(16, R.drawable.ic_action_menyanyi_dangdut, R.string.caborMenyanyiDangdut));
        caborListppUBTV.add(new CabangOlahraga(18, R.drawable.ic_action_menyanyi_pop, R.string.caborMenyanyiPop));
        caborListppUBTV.add(new CabangOlahraga(33, R.drawable.ic_action_vocal_grup, R.string.caborVocalGrup));
        caborListppUBTV.add(new CabangOlahraga(28, R.drawable.ic_action_sinematografi, R.string.caborSinematografi));
        caborListppUBTV.add(new CabangOlahraga(21, R.drawable.ic_action_vocal_grup, R.string.caborPaduanSuara));

        ArrayList<CabangOlahraga> caborListLapParkirGerbangWatugong = new ArrayList<>();
        caborListLapParkirGerbangWatugong.add(new CabangOlahraga(22, R.drawable.ic_action_panahan, R.string.caborPanahan));

        ArrayList<CabangOlahraga> caborListLapUBDieng = new ArrayList<>();
        caborListLapUBDieng.add(new CabangOlahraga(0, R.drawable.ic_action_help, R.string.sepak_bola_ada_nggk_sih));

        ArrayList<CabangOlahraga> caborListSakri = new ArrayList<>();
        caborListSakri.add(new CabangOlahraga(23, R.drawable.ic_action_pencak_silat, R.string.caborPencakSilat));

        ArrayList<CabangOlahraga> caborListUB = new ArrayList<>();
        caborListUB.add(new CabangOlahraga(0, R.drawable.ic_action_help, R.string.sepeda_ada_nggak_sih));

        ArrayList<CabangOlahraga> caborListKolamRenangGajayana = new ArrayList<>();
        caborListKolamRenangGajayana.add(new CabangOlahraga(27, R.drawable.ic_action_renang, R.string.caborRenang));

        ArrayList<CabangOlahraga> caborListGazeboUB = new ArrayList<>();
        caborListGazeboUB.add(new CabangOlahraga(9, R.drawable.ic_action_festival_band, R.string.caborFestivalBand));

        ArrayList<CabangOlahraga> caborListGorPertamina = new ArrayList<>();
        caborListGorPertamina.add(new CabangOlahraga(11, R.drawable.ic_action_futsal, R.string.caborFutsal));
        caborListGorPertamina.add(new CabangOlahraga(3, R.drawable.ic_action_basket, R.string.caborBasket));

        ArrayList<CabangOlahraga> caborListPerpusUB = new ArrayList<>();
        caborListPerpusUB.add(new CabangOlahraga(8, R.drawable.ic_action_desain_poster, R.string.caborDesainPoster));
        caborListPerpusUB.add(new CabangOlahraga(14, R.drawable.ic_action_komik, R.string.caborKomik));
        caborListPerpusUB.add(new CabangOlahraga(10, R.drawable.ic_action_fotografi, R.string.caborFotografi));
        caborListPerpusUB.add(new CabangOlahraga(10, R.drawable.ic_action_fotografi, R.string.caborFotografi));
        caborListPerpusUB.add(new CabangOlahraga(0, R.drawable.ic_action_help, R.string.lukis_ada_nggak_sih)); // di ... excel ada lukis?

        ArrayList<CabangOlahraga> caborListStadionGajayana = new ArrayList<>();
        caborListStadionGajayana.add(new CabangOlahraga(1, R.drawable.ic_action_atletik, R.string.caborAtletik));

        PetunjukPenonton ppUBSC = new PetunjukPenonton(1, R.string.venueUBSC, R.drawable.ubsc, caborListppUSBC, -7.952281, 112.612732);
        PetunjukPenonton ppGKM = new PetunjukPenonton(2, R.string.venueGKM, R.drawable.tempat_gkm, caborListppGKM, -7.954671, 112.618540);
        PetunjukPenonton ppWidyaloka = new PetunjukPenonton(3, R.string.venueWidyaloka, R.drawable.widlok, caborListppWidyaloka, -7.954671, 112.618540);
        PetunjukPenonton ppLapSampingGorPertamina = new PetunjukPenonton(4, R.string.venueLapanganSampingGorPertamina, R.drawable.ic_action_help, caborListLapSampingGorPertamina, -7.954002, 112.616504);
        PetunjukPenonton ppSatasiunMalang = new PetunjukPenonton(5, R.string.venumeStasiunMalang, R.drawable.ic_action_help, caborListStasiunMalang, -7.9774991, 112.6369667);
        PetunjukPenonton ppUBTV = new PetunjukPenonton(6, R.string.venueUBTV, R.drawable.ubtv, caborListppUBTV, -7.952281, 112.612732);
        PetunjukPenonton ppLapParkirGerbangWatugong = new PetunjukPenonton(7, R.string.venueLapanganParkirGerbangWatugong, R.drawable.ic_action_help, caborListLapParkirGerbangWatugong, -7.947952, 112.612895);
        PetunjukPenonton ppLapUBDieng = new PetunjukPenonton(8, R.string.venueLapanganUBDieng, R.drawable.ic_action_help, caborListLapUBDieng, -7.970072, 112.592061);
        PetunjukPenonton ppGedungSakri = new PetunjukPenonton(9, R.string.venueSakri, R.drawable.tempat_gedung_sakri, caborListSakri, -7.9528741, 112.6160763);
        PetunjukPenonton ppUB = new PetunjukPenonton(10, R.string.ub, R.drawable.tempat_ub, caborListUB, -7.9526075, 112.6144582);
        PetunjukPenonton ppKolamRenangGajayana = new PetunjukPenonton(11, R.string.venueGajayana, R.drawable.gajayana, caborListKolamRenangGajayana, -7.976156, 112.624374);
        PetunjukPenonton ppGazeboUB = new PetunjukPenonton(12, R.string.venueGazeboUB, R.drawable.gazebo_ub, caborListGazeboUB, -7.955715, 112.612883);
        PetunjukPenonton ppGorPertaminaUB = new PetunjukPenonton(13, R.string.venueGorPertamina, R.drawable.tempat_gor_pertamina_ub, caborListGorPertamina, -7.953503, 112.616319);
        PetunjukPenonton ppPerpusUB = new PetunjukPenonton(14, R.string.venuePerpusUB, R.drawable.tempat_perpustakaan_ub, caborListPerpusUB, -7.953156, 112.6137222);
        PetunjukPenonton ppStadionGajayana = new PetunjukPenonton(15, R.string.venueStadionGajayana, R.drawable.tempat_stadion_gajayana, caborListStadionGajayana, -7.9755852, 112.6250749);

        ArrayList<PetunjukPenonton> list = new ArrayList<>();
        list.add(ppUBSC);
        list.add(ppGKM);
        list.add(ppWidyaloka);
        list.add(ppLapSampingGorPertamina);
        list.add(ppSatasiunMalang);

        list.add(ppUBTV);
        list.add(ppLapParkirGerbangWatugong);
        list.add(ppLapUBDieng);
        list.add(ppGedungSakri);
        list.add(ppUB);

        list.add(ppKolamRenangGajayana);
        list.add(ppGazeboUB);
        list.add(ppGorPertaminaUB);
        list.add(ppPerpusUB);
        list.add(ppStadionGajayana);

        return list;
    }

    ;

    // derpecated
    public static void renderIconWithColorOf(String hexColor, Context context) {
        Toast.makeText(context, "renderIconWithColorOf begin...", Toast.LENGTH_SHORT).show();
        // render color
        // https://stackoverflow.com/questions/11376516/change-drawable-color-programmatically
        // https://stackoverflow.com/questions/5248583/how-to-get-a-color-from-hexadecimal-color-string
        ArrayList<Drawable> drawableArrayList = new ArrayList<>();
        for (int i = 0; i < DataList.cabangOlahragaList.length; i++) {
            int currentIconRes = DataList.cabangOlahragaList[i].getCaborIconRes();
            Drawable drawable = ContextCompat.getDrawable(context, currentIconRes);
            drawable.setColorFilter(Color.parseColor(hexColor), PorterDuff.Mode.SRC_IN);
            // drawableArrayList.add(drawable);
        }

        // render navigation icon ke ungu
        for (int i = 0; i < DataList.navItemList.length; i++) {
            int currentIconRes = DataList.navItemList[i].getNavIconRes();
            Drawable drawable = ContextCompat.getDrawable(context, currentIconRes);
            drawable.setColorFilter(Color.parseColor(hexColor), PorterDuff.Mode.SRC_IN);
            // drawableArrayList.add(drawable);
        }

        // other icon
        for (int i = 0; i < DataList.otherRenderableDrawables.length; i++) {
            int currentIconRes = DataList.otherRenderableDrawables[i].getDrawableIconRes();
            Drawable drawable = ContextCompat.getDrawable(context, currentIconRes);
            drawable.setColorFilter(Color.parseColor(hexColor), PorterDuff.Mode.SRC_IN);
        }
//        Drawable drawableShareIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_share);
//        drawableShareIcon.setColorFilter(Color.parseColor(hexColor), PorterDuff.Mode.SRC_IN);

        Toast.makeText(context, "renderIconWithColorOf done...", Toast.LENGTH_SHORT).show();


    }


    public static void initialize(Context mContext) {

        for (CabangOlahraga cabangOlahraga : cabangOlahragaList) {
            cabangOlahraga.setCaborName(mContext.getResources().getString(cabangOlahraga.getCaborNameRes()));
        }

        for (Fakultas fakultas : fakultasList) {
            fakultas.setFakultasName(mContext.getResources().getString(fakultas.getFakultasNameRes()));
        }

        for (NavigationItem navigationItem : navItemList) {
            navigationItem.setNavName(mContext.getResources().getString(navigationItem.getNavNameRes()));
        }
        for (PetunjukPenonton petunjukPenonton : petunjukPenontonList) {
            petunjukPenonton.setVenueName(mContext.getResources().getString(petunjukPenonton.getVenueNameRes()));
        }

    }

    @Nullable
    public static CabangOlahraga getCabangOlahragaObject(String namaCabor) {
        for (CabangOlahraga current : cabangOlahragaList) {
            if (current.getCaborName().equalsIgnoreCase(namaCabor)) return current;
        }
        return null;
    }

    @Nullable
    public static Fakultas getFakultasObject(String namaFakultas) {
        for (Fakultas current : fakultasList) {
            if (current.getFakultasName().equalsIgnoreCase(namaFakultas)) return current;
        }
        return null;
    }

    @Nullable
    public static PetunjukPenonton getPetunjukPenontonObject(String namaVenue) {
        for (PetunjukPenonton current : petunjukPenontonList) {
            if (current.getVenueName().equalsIgnoreCase(namaVenue)) return current;
        }
        return null;
    }
}
