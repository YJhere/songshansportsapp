package com.example.newsong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class locationActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;

    private static final int REQUEST_CODE = 101;

    private boolean permissionDenied = false;

    TextView txvInfo;

    Location currentLocation;

    FusedLocationProviderClient fusedLocationProviderClient;

    PolylineOptions polyline;

    TextView txtlocat;

    private GoogleMap googleMap;

    final LatLng T = new LatLng(25.0513848, 121.5475527);
    final LatLng TAIPEI = new LatLng(25.0495735, 121.5515556);
    final LatLng M = new LatLng(25.0572456919806, 121.55900777345303);
    final LatLng FS = new LatLng(25.049313969408665, 121.5493427698455);
    final LatLng SS = new LatLng(25.0594659, 121.5491421);
    final LatLng f = new LatLng(25.065038351829514, 121.56666373196342);
    final LatLng k = new LatLng(25.051772, 121.570968);
    final LatLng mi = new LatLng(25.061652, 121.5573163);
    final LatLng g = new LatLng(25.0594659, 121.5491421);//金鐘

    final LatLng j = new LatLng(25.053864746535577, 121.54664114794234);
    final LatLng d = new LatLng(25.055520829701468, 121.54499817533123);
    final LatLng Si = new LatLng(25.05403526000034, 121.56392845728507);
    final LatLng gs = new LatLng(25.05074248417915, 121.56388870110395);
    final LatLng fss = new LatLng(25.047546303268547, 121.56143047175998);
    final LatLng sAn = new LatLng(25.062199918397948, 121.56833575003357);
    final LatLng gh = new LatLng(25.047989105945426, 121.56111060181658);
    final LatLng gz = new LatLng(25.061610552653665, 121.55428212877463);

    final LatLng mc = new LatLng(25.06192865796767, 121.55721752371839);
    final LatLng fu = new LatLng(25.04677301035663, 121.55461490639608);
    final LatLng mt = new LatLng(25.059700866062776, 121.55132558600431);
    final LatLng fj = new LatLng(25.047170, 121.557260);
    final LatLng sz = new LatLng(25.060690, 121.560366);
    final LatLng sl = new LatLng(25.060272, 121.554941);
    final LatLng fm = new LatLng(25.0602783,121.5635023);
    final LatLng mz = new LatLng(25.059700866062776, 121.55132558600431);
    final LatLng m7 = new LatLng(25.06217635548174, 121.56043102161767);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ImageButton b1=findViewById(R.id.b1);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(locationActivity.this, ImageActivity.class);
               TextView txv=findViewById(R.id.txtlocat);
                 String message = txv.getText().toString();
                intent.putExtra("p_EXTRA", message);
                startActivity(intent);
            }
        });

        /**折線*/
        //polyline = new PolylineOptions();
        //polyline.color(Color.BLUE);
        //polyline.width(10);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Add a marker in Sydney and move the camera

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        computeWindowSizeClasses();

    }  private void computeWindowSizeClasses() {
        WindowMetrics metrics = WindowMetricsCalculator.getOrCreate()
                .computeCurrentWindowMetrics(this);

        float widthDp = metrics.getBounds().width() /
                getResources().getDisplayMetrics().density;
        WindowSizeClass widthWindowSizeClass;

        if (widthDp < 600f) {
            widthWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (widthDp < 840f) {
            widthWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            widthWindowSizeClass = WindowSizeClass.EXPANDED;
        }

        float heightDp = metrics.getBounds().height() /
                getResources().getDisplayMetrics().density;
        WindowSizeClass heightWindowSizeClass;

        if (heightDp < 480f) {
            heightWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (heightDp < 900f) {
            heightWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            heightWindowSizeClass = WindowSizeClass.EXPANDED;
        }

        // Use widthWindowSizeClass and heightWindowSizeClass.
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        /**設置我的位置按鈕*/
        mMap.setOnMyLocationButtonClickListener((GoogleMap.OnMyLocationButtonClickListener) locationActivity.this);
        mMap.setOnMyLocationClickListener((GoogleMap.OnMyLocationClickListener) locationActivity.this);//設置監聽器
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);//上面已經要求過Permission，所以無須理會

            // Add a marker in Sydney and move the camera
         //   LatLng curPos = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(curPos).title("您的位置"));
         //   mMap.animateCamera(CameraUpdateFactory.newLatLng(curPos));



        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions() //台北小巨蛋
                .clickable(true)
                .add(
                        new LatLng(25.05148079286083, 121.54935795576425),
                        new LatLng(25.051446396977664, 121.55098423344482),
                        new LatLng(25.049864176115676, 121.55073111629504),
                        new LatLng(25.049950166899556, 121.54922506925845),
                        new LatLng(25.05148079286083, 121.54935795576425)));
        polyline1.setTag("A");
        polyline1.setColor(Color.BLUE);

        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions() //民生公園
                .clickable(true)
                .add(
                        new LatLng(25.057683921412636, 121.55713191638961),
                        new LatLng(25.057808399504843, 121.5589722806105),
                        new LatLng(25.0572456919806, 121.55900777345303),
                        new LatLng(25.057099905506792, 121.55722142223513),
                        new LatLng(25.057683921412636, 121.55713191638961)));
        polyline2.setTag("B");
        polyline2.setColor(Color.BLUE);

        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions() //台北田徑場
                .clickable(true)
                .add(
                        new LatLng(25.05006492310075, 121.55154055643246),
                        new LatLng(25.04987306612887, 121.55181650823032),
                        new LatLng(25.049687022717894, 121.55189351803438),
                        new LatLng(25.049466095800646, 121.551822925714),
                        new LatLng(25.04934400443873, 121.55171382849157),
                        new LatLng(25.04934400443873, 121.55165607113852),
                        new LatLng( 25.04926842401089, 121.55157906133445),
                        new LatLng( 25.04941377094614, 121.55127102211823),
                        new LatLng( 25.04965795340942, 121.55122609973255),
                        new LatLng( 25.049739347455755, 121.55119401231417),
                        new LatLng( 25.049826555302577, 121.55119401231417),
                        new LatLng( 25.050053295414017, 121.55137370185697),
                        new LatLng(25.05006492310075, 121.55154055643246)));
        polyline3.setTag("C");
        polyline3.setColor(Color.BLUE);


        Polyline polyline4 = googleMap.addPolyline(new PolylineOptions() //台北暖身場
                .clickable(true)
                .add(
                        new LatLng(25.049313969408665, 121.5493427698455),
                        new LatLng(25.04930959778516, 121.54988322489058),
                        new LatLng(25.048640737549572, 121.54988805038204),
                        new LatLng(25.048640737549572, 121.54935242082843),
                        new LatLng(25.049313969408665, 121.5493427698455)));
        polyline4.setTag("D");
        polyline4.setColor(Color.BLUE);


        Polyline polyline5 = googleMap.addPolyline(new PolylineOptions() //臺北市松山區民族國民小學
                .clickable(true)
                .add(
                        new LatLng(25.059400, 121.551175),
                        new LatLng(25.059464, 121.551382),
                        new LatLng(25.059646, 121.551462),
                        new LatLng(25.059847, 121.551521),
                        new LatLng(25.060008, 121.551422),
                        new LatLng( 25.060008, 121.551283),
                        new LatLng( 25.059872, 121.551162),
                        new LatLng( 25.059607, 121.551033),
                        new LatLng( 25.059546, 121.551047),
                        new LatLng( 25.059400, 121.551175)


                        ));
        polyline5.setTag("E");
        polyline5.setColor(Color.BLUE);


        Polyline polyline6 = googleMap.addPolyline(new PolylineOptions() //復源公園

                .clickable(true)
                .add(
                        new LatLng(25.04677301035663, 121.55461490639608),
                        new LatLng(25.046373326951993, 121.5546678467036),
                        new LatLng(25.046307379065016, 121.5542421183973),
                        new LatLng(25.04673504048921, 121.55432594055085),
                        new LatLng(25.04677301035663, 121.55461490639608)));
        polyline6.setTag("F");
        polyline6.setColor(Color.BLUE);


        Polyline polyline7 = googleMap.addPolyline(new PolylineOptions() //民權運動公園

                .clickable(true)
                .add(
                        new LatLng(25.06192865796767, 121.55721752371839),
                        new LatLng(25.062302828153527, 121.56009285185239),
                        new LatLng(25.061510752253454, 121.56016258928848),
                        new LatLng(25.061049110563378, 121.55708341326438),
                        new LatLng(25.06192865796767, 121.55721752371839)));
        polyline7.setTag("G");
        polyline7.setColor(Color.BLUE);


        Polyline polyline8 = googleMap.addPolyline(new PolylineOptions() //

                .clickable(true)
                .add(
                        new LatLng(25.06217635548174, 121.56043102161767),
                        new LatLng(25.062324550855934, 121.56124901407505),
                        new LatLng(25.06203985958377, 121.5613308133208),
                        new LatLng(25.061907263423002, 121.56045254773497),
                        new LatLng(25.06217635548174, 121.56043102161767)));
        polyline8.setTag("H");
        polyline8.setColor(Color.BLUE);

        Polyline polyline9 = googleMap.addPolyline(new PolylineOptions() //

                .clickable(true)
                .add(
                        new LatLng(25.061610552653665, 121.55428212877463),
                        new LatLng(25.058539390367663, 121.55490440128125),
                        new LatLng(25.058014563795382, 121.5495399831208),
                        new LatLng(25.0613207873474, 121.55112065735185),
                        new LatLng(25.061610552653665, 121.55428212877463)));
        polyline9.setTag("I");
        polyline9.setColor(Color.BLUE);


        Polyline polyline10 = googleMap.addPolyline(new PolylineOptions() //京華捐地

                .clickable(true)
                .add(
                        new LatLng(25.047989105945426, 121.56111060181658),
                        new LatLng(25.047962532639225, 121.56134525672601),
                        new LatLng(25.04802453701141, 121.56155546841569),
                        new LatLng(25.04741334968809, 121.56167768451434),
                        new LatLng(25.047470925435476, 121.56178523468117),
                        new LatLng(25.047484212142585, 121.56221543534843),
                        new LatLng(25.047191904253765, 121.56236698331078),
                        new LatLng(25.04674015432759, 121.56123281791521),
                        new LatLng(25.047989105945426, 121.56111060181658)));
        polyline10.setTag("J");
        polyline10.setColor(Color.RED);

        Polyline polyline11 = googleMap.addPolyline(new PolylineOptions() //三民公園

                .clickable(true)
                .add(
                        new LatLng(25.063301680685637, 121.56783176573548),
                        new LatLng(25.06334124655006, 121.56814087610688),
                        new LatLng(25.062199918397948, 121.56833575003357),
                        new LatLng(25.061079884698216, 121.56687755546785),
                        new LatLng(25.061450, 121.566515),
                        new LatLng(25.061722, 121.566963),
                         new LatLng(25.061907, 121.566978),
                        new LatLng(25.06203795883638, 121.56767974907869),
                        new LatLng(25.06265580778329, 121.5679646632972),
                        new LatLng(25.063301680685637, 121.56783176573548)));
        polyline11.setTag("K");
        polyline11.setColor(Color.BLUE);

        Polyline polyline12 = googleMap.addPolyline(new PolylineOptions() //復盛公園

                .clickable(true)
                .add(
                        new LatLng(25.047569222621323, 121.561158512783),
                        new LatLng(25.047592141969822, 121.56140517325046),
                        new LatLng(25.047546303268547, 121.56143047175998),
                        new LatLng(25.047437436284305, 121.56181627402961),
                        new LatLng(25.047443166127987, 121.5621957516719),
                        new LatLng(25.047195222411453, 121.56230049660509),
                        new LatLng(25.0467309238208, 121.56125263167988),
                        new LatLng(25.047569222621323, 121.561158512783)));
        polyline12.setTag("L");
        polyline12.setColor(Color.BLUE);

        Polyline polyline13 = googleMap.addPolyline(new PolylineOptions() //吉祥公園

                .clickable(true)
                .add(
                        new LatLng(25.05074248417915, 121.56388870110395),
                        new LatLng(25.05074248417915, 121.56418762715396),
                        new LatLng(25.05038641518334, 121.56434090423491),
                        new LatLng(25.05031963080041, 121.56382495183797),
                        new LatLng(25.05074248417915, 121.56388870110395)));
        polyline13.setTag("M");
        polyline13.setColor(Color.BLUE);

        Polyline polyline14 = googleMap.addPolyline(new PolylineOptions() //西松公園

                .clickable(true)
                .add(
                        new LatLng(25.05403526000034, 121.56392845728507),
                        new LatLng(25.054039156583222, 121.56437579220136),
                        new LatLng(25.053446874563132, 121.56436718960681),
                        new LatLng(25.053454667766196, 121.5639241559878),
                        new LatLng(25.05403526000034, 121.56392845728507)));
        polyline14.setTag("N");
        polyline14.setColor(Color.BLUE);


        Polyline polyline15 = googleMap.addPolyline(new PolylineOptions() //松基公園

                .clickable(true)
                .add(
                        new LatLng(25.055520829701468, 121.54499817533123),
                        new LatLng(25.0557186654366, 121.54544779470007),
                        new LatLng(25.055800127117077, 121.54635987970546),
                        new LatLng(25.05535790591511, 121.54601303047804),
                        new LatLng(25.055253169080935, 121.54569829691987),
                        new LatLng(25.055037876418496, 121.54537071709397),
                        new LatLng(25.054869133256524, 121.54507525293732),
                        new LatLng(25.054598028803085, 121.54506452087931),
                        new LatLng(25.05460877781724, 121.54481534530042),
                        new LatLng(25.055066684944943, 121.54481534530042),
                        new LatLng(25.055296712772424, 121.54507875948264),
                        new LatLng(25.055520829701468, 121.54499817533123)));
        polyline15.setTag("O");
        polyline15.setColor(Color.BLUE);

        Polyline polyline16 = googleMap.addPolyline(new PolylineOptions() //慶城公園

                .clickable(true)
                .add(
                        new LatLng(25.053864746535577, 121.54664114794234),
                        new LatLng(25.052962269843963, 121.54665443069423),
                        new LatLng(25.052986335975294, 121.54593052071559),
                        new LatLng(25.053864746535577, 121.54664114794234)));
        polyline16.setTag("P");
        polyline16.setColor(Color.BLUE);


        Polyline polyline17 = googleMap.addPolyline(new PolylineOptions() //觀山河濱公園(彩虹橋到中山高速公路)
                .clickable(true)
                .add(
                        new LatLng(25.051772, 121.570968),
                        new LatLng(25.055748759695206, 121.57092013305794),

        new LatLng( 25.058863, 121.570533),
                        new LatLng(25.063572475051867, 121.56865634865254),
                        new LatLng(25.067405, 121.570054),
                        new LatLng(    25.068483, 121.570418),
                        new LatLng(25.070647, 121.570973)));
        polyline17.setTag("Q");
        polyline17.setColor(Color.BLUE);

        Polyline polyline18 = googleMap.addPolyline(new PolylineOptions() //撫遠公園

                .clickable(true)
                .add(
                        new LatLng(25.065038351829514, 121.56666373196342),
                        new LatLng(25.06514847919053, 121.5677883154187),
                        new LatLng(25.06384346359836, 121.56696159460832),
                        new LatLng(25.065038351829514, 121.56666373196342)));
        polyline18.setTag("R");
        polyline18.setColor(Color.BLUE);

        Polyline polyline19 = googleMap.addPolyline(new PolylineOptions() //復健綠底

                .clickable(true)
                .add(
                        new LatLng(25.047170, 121.557260),
                        new LatLng(25.047524, 121.557180),
                        new LatLng(25.047579, 121.557703),
                        new LatLng(25.047601, 121.557699),
                        new LatLng(25.047220, 121.557680),
                        new LatLng(25.047170, 121.557260)));
        polyline19.setTag("R");
        polyline19.setColor(Color.BLUE);
        //   mMap.addMarker(new MarkerOptions().position(new LatLng(25.065038351829514, 121.56666373196342)).title(""));
        Polyline polyline20 = googleMap.addPolyline(new PolylineOptions() //新中

                .clickable(true)
                .add(
                        new LatLng(25.060690, 121.560366),
                        new LatLng(25.060388, 121.560395),
                        new LatLng(25.060451, 121.561379),
                        new LatLng(25.060758, 121.561352),
                        new LatLng(25.060690, 121.560366)));
        polyline20.setTag("R");
        polyline20.setColor(Color.BLUE);

           Polyline polyline21 = googleMap.addPolyline(new PolylineOptions() //松茸

                .clickable(true)
                .add(
                        new LatLng(25.060272, 121.554941),
                        new LatLng(25.059914, 121.555013),
                        new LatLng(25.060047, 121.556808),
                        new LatLng(25.060414, 121.556886),
                        new LatLng(25.060272, 121.554941)));
        polyline21.setTag("R");
        polyline21.setColor(Color.BLUE);
        //   mMap.addMarker(new MarkerOptions().position(new LatLng(25.065038351829514, 121.56666373196342)).title(""));
        Polyline polyline22 = googleMap.addPolyline(new PolylineOptions() //富民

                .clickable(true)
                .add(
                        new LatLng(25.060145, 121.563402),
                        new LatLng(25.060259, 121.564550),
                        new LatLng(25.060649, 121.565200),
                        new LatLng(25.060699, 121.565202),
                        new LatLng(25.060717, 121.565467),

                new LatLng(25.060491, 121.565560),
                new LatLng(25.060483, 121.565521),
                new LatLng(25.060197, 121.565353),
                new LatLng(25.060079, 121.565069),
                new LatLng(25.060081, 121.564462),
                new LatLng(25.059996, 121.564213),
                new LatLng(25.059902, 121.563949),
                new LatLng(25.059869, 121.563665),
                        new LatLng(25.059929, 121.563401),
                        new LatLng(25.059938, 121.563431), new LatLng(25.060156, 121.563382)));
        polyline22.setTag("R");
        polyline22.setColor(Color.BLUE);

        Intent intent = getIntent();
        String message = intent.getStringExtra("m_EXTRA");
        TextView txtlocat=findViewById(R.id.txtlocat);
        txtlocat.setText(message);

        if(Objects.equals(message, "台北小巨蛋")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(T)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.05148079286083, 121.54935795576425)).title("台北小巨蛋"));

        }else if(Objects.equals(message, "台北田徑場")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(TAIPEI)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.05006492310075, 121.55154055643246)).title("台北田徑場"));


        }else if(Objects.equals(message, "民生公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(M)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              mMap.addMarker(new MarkerOptions().position(new LatLng(25.0572456919806, 121.55900777345303)).title("民生公園"));

        }else if(Objects.equals(message, "台北暖身場")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(FS)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.049313969408665, 121.5493427698455)).title("台北暖身場"));

        }else if(Objects.equals(message, "民族國小")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(mz)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              mMap.addMarker(new MarkerOptions().position(new LatLng(25.059700866062776, 121.55132558600431)).title("民族國小"));

        }else if(Objects.equals(message, "民權國小")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(m7)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
           mMap.addMarker(new MarkerOptions().position(new LatLng(25.06217635548174, 121.56043102161767)).title("民權國小"));

        }else if(Objects.equals(message, "精忠社區")){
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(gz)              // Sets the center of the map to ZINTUN
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
             mMap.addMarker(new MarkerOptions().position(new LatLng(25.061610552653665, 121.55428212877463)).title("精忠社區"));

        }else if(Objects.equals(message, "京華捐地")){
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(gh)              // Sets the center of the map to ZINTUN
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
               mMap.addMarker(new MarkerOptions().position(new LatLng(25.047989105945426, 121.56111060181658)).title("京華捐地"));

        }else if(Objects.equals(message, "三民公園")){
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(sAn)              // Sets the center of the map to ZINTUN
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
               mMap.addMarker(new MarkerOptions().position(new LatLng(25.062199918397948, 121.56833575003357)).title("三民公園"));

        }else if(Objects.equals(message, "復盛公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fss)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().position(new LatLng(25.047569222621323, 121.561158512783)).title("復盛公園"));

        }else if(Objects.equals(message, "吉祥公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(gs)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
         mMap.addMarker(new MarkerOptions().position(new LatLng(25.05074248417915, 121.56388870110395)).title("吉祥公園"));

        }else if(Objects.equals(message, "西松公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(Si)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
               mMap.addMarker(new MarkerOptions().position(new LatLng(25.05403526000034, 121.56392845728507)).title("西松公園"));

        }else if(Objects.equals(message, "松基公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(d)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
               mMap.addMarker(new MarkerOptions().position(new LatLng(25.055520829701468, 121.54499817533123)).title("松基公園"));

        }else if(Objects.equals(message, "觀山河濱公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(k)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
             mMap.addMarker(new MarkerOptions().position(new LatLng(25.051772, 121.570968)).title("觀山河濱公園"));

        }else if(Objects.equals(message, "撫遠公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(f)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.065038351829514, 121.56666373196342)).title("撫遠公園"));
        }else if(Objects.equals(message, "復源公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fu)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.04677301035663, 121.55461490639608)).title("復源公園"));

        }else if(Objects.equals(message, "慶城公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(j)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.addMarker(new MarkerOptions().position(new LatLng(25.053864746535577, 121.54664114794234)).title("慶城公園"));

        }else if(Objects.equals(message, "民權運動公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(mc)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
               mMap.addMarker(new MarkerOptions().position(new LatLng(25.06192865796767, 121.55721752371839)).title("民權運動公園"));

        }else if(Objects.equals(message, "富民生態公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fm)              // Sets the center of the map to ZINTUN
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              mMap.addMarker(new MarkerOptions().position(new LatLng(25.060156, 121.563382)).title("富民生態公園"));

        }else if(Objects.equals(message, "松榮公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(sl)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.060272, 121.554941)).title("松榮公園"));

        }else if(Objects.equals(message, "新中公園")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(sz)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.060690, 121.560366)).title("新中公園"));

        }else if(Objects.equals(message, "復建綠地")){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fj)              // Sets the center of the map to ZINTUN
                    .zoom(18)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.047170, 121.557260)).title("復建綠地"));

        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }



}