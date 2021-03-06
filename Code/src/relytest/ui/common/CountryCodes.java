/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.ui.common;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class CountryCodes {

    final Map<String, String> mapCountries;
    final Map<String, String> mapCodes;
 
    public CountryCodes() {
        this.mapCodes = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        this.mapCountries = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

     mapCountries.put("Andorra, Principality Of", "AD");
     mapCountries.put("United Arab Emirates", "AE");
     mapCountries.put("Afghanistan, Islamic State Of", "AF");
     mapCountries.put("Antigua And Barbuda", "AG");
     mapCountries.put("Anguilla", "AI");
     mapCountries.put("Albania", "AL");
     mapCountries.put("Armenia", "AM");
     mapCountries.put("Netherlands Antilles", "AN");
     mapCountries.put("Angola", "AO");
     mapCountries.put("Antarctica", "AQ");
     mapCountries.put("Argentina", "AR");
     mapCountries.put("American Samoa", "AS");
     mapCountries.put("Austria", "AT");
     mapCountries.put("Australia", "AU");
     mapCountries.put("Aruba", "AW");
     mapCountries.put("Azerbaidjan", "AZ");
     mapCountries.put("Bosnia-Herzegovina", "BA");
     mapCountries.put("Barbados", "BB");
     mapCountries.put("Bangladesh", "BD");
     mapCountries.put("Belgium", "BE");
     mapCountries.put("Burkina Faso", "BF");
     mapCountries.put("Bulgaria", "BG");
     mapCountries.put("Bahrain", "BH");
     mapCountries.put("Burundi", "BI");
     mapCountries.put("Benin", "BJ");
     mapCountries.put("Bermuda", "BM");
     mapCountries.put("Brunei Darussalam", "BN");
     mapCountries.put("Bolivia", "BO");
     mapCountries.put("Brazil", "BR");
     mapCountries.put("Bahamas", "BS");
     mapCountries.put("Bhutan", "BT");
     mapCountries.put("Bouvet Island", "BV");
     mapCountries.put("Botswana", "BW");
     mapCountries.put("Belarus", "BY");
     mapCountries.put("Belize", "BZ");
     mapCountries.put("Canada", "CA");
     mapCountries.put("Cocos (Keeling) Islands", "CC");
     mapCountries.put("Central African Republic", "CF");
     mapCountries.put("Congo, The Democratic Republic Of The", "CD");
     mapCountries.put("Congo", "CG");
     mapCountries.put("Switzerland", "CH");
     mapCountries.put("Ivory Coast (Cote D'Ivoire)", "CI");
     mapCountries.put("Cook Islands", "CK");
     mapCountries.put("Chile", "CL");
     mapCountries.put("Cameroon", "CM");
     mapCountries.put("China", "CN");
     mapCountries.put("Colombia", "CO");
     mapCountries.put("Costa Rica", "CR");
     mapCountries.put("Former Czechoslovakia", "CS");
     mapCountries.put("Cuba", "CU");
     mapCountries.put("Cape Verde", "CV");
     mapCountries.put("Christmas Island", "CX");
     mapCountries.put("Cyprus", "CY");
     mapCountries.put("Czech Republic", "CZ");
     mapCountries.put("Germany", "DE");
     mapCountries.put("Djibouti", "DJ");
     mapCountries.put("Denmark", "DK");
     mapCountries.put("Dominica", "DM");
     mapCountries.put("Dominican Republic", "DO");
     mapCountries.put("Algeria", "DZ");
     mapCountries.put("Ecuador", "EC");
     mapCountries.put("Estonia", "EE");
     mapCountries.put("Egypt", "EG");
     mapCountries.put("Western Sahara", "EH");
     mapCountries.put("Eritrea", "ER");
     mapCountries.put("Spain", "ES");
     mapCountries.put("Ethiopia", "ET");
     mapCountries.put("Finland", "FI");
     mapCountries.put("Fiji", "FJ");
     mapCountries.put("Falkland Islands", "FK");
     mapCountries.put("Micronesia", "FM");
     mapCountries.put("Faroe Islands", "FO");
     mapCountries.put("France", "FR");
     mapCountries.put("France (European Territory)", "FX");
     mapCountries.put("Gabon", "GA");
     mapCountries.put("Great Britain", "UK");
     mapCountries.put("Grenada", "GD");
     mapCountries.put("Georgia", "GE");
     mapCountries.put("French Guyana", "GF");
     mapCountries.put("Ghana", "GH");
     mapCountries.put("Gibraltar", "GI");
     mapCountries.put("Greenland", "GL");
     mapCountries.put("Gambia", "GM");
     mapCountries.put("Guinea", "GN");
     mapCountries.put("Guadeloupe (French)", "GP");
     mapCountries.put("Equatorial Guinea", "GQ");
     mapCountries.put("Greece", "GR");
     mapCountries.put("S. Georgia & S. Sandwich Isls.", "GS");
     mapCountries.put("Guatemala", "GT");
     mapCountries.put("Guam (USA)", "GU");
     mapCountries.put("Guinea Bissau", "GW");
     mapCountries.put("Guyana", "GY");
     mapCountries.put("Hong Kong", "HK");
     mapCountries.put("Heard And McDonald Islands", "HM");
     mapCountries.put("Honduras", "HN");
     mapCountries.put("Croatia", "HR");
     mapCountries.put("Haiti", "HT");
     mapCountries.put("Hungary", "HU");
     mapCountries.put("Indonesia", "ID");
     mapCountries.put("Ireland", "IE");
     mapCountries.put("Israel", "IL");
     mapCountries.put("India", "IN");
     mapCountries.put("British Indian Ocean Territory", "IO");
     mapCountries.put("Iraq", "IQ");
     mapCountries.put("Iran", "IR");
     mapCountries.put("Iceland", "IS");
     mapCountries.put("Italy", "IT");
     mapCountries.put("Jamaica", "JM");
     mapCountries.put("Jordan", "JO");
     mapCountries.put("Japan", "JP");
     mapCountries.put("Kenya", "KE");
     mapCountries.put("Kyrgyz Republic (Kyrgyzstan)", "KG");
     mapCountries.put("Cambodia, Kingdom Of", "KH");
     mapCountries.put("Kiribati", "KI");
     mapCountries.put("Comoros", "KM");
     mapCountries.put("Saint Kitts & Nevis Anguilla", "KN");
     mapCountries.put("North Korea", "KP");
     mapCountries.put("South Korea", "KR");
     mapCountries.put("Kuwait", "KW");
     mapCountries.put("Cayman Islands", "KY");
     mapCountries.put("Kazakhstan", "KZ");
     mapCountries.put("Laos", "LA");
     mapCountries.put("Lebanon", "LB");
     mapCountries.put("Saint Lucia", "LC");
     mapCountries.put("Liechtenstein", "LI");
     mapCountries.put("Sri Lanka", "LK");
     mapCountries.put("Liberia", "LR");
     mapCountries.put("Lesotho", "LS");
     mapCountries.put("Lithuania", "LT");
     mapCountries.put("Luxembourg", "LU");
     mapCountries.put("Latvia", "LV");
     mapCountries.put("Libya", "LY");
     mapCountries.put("Morocco", "MA");
     mapCountries.put("Monaco", "MC");
     mapCountries.put("Moldavia", "MD");
     mapCountries.put("Madagascar", "MG");
     mapCountries.put("Marshall Islands", "MH");
     mapCountries.put("Macedonia", "MK");
     mapCountries.put("Mali", "ML");
     mapCountries.put("Myanmar", "MM");
     mapCountries.put("Mongolia", "MN");
     mapCountries.put("Macau", "MO");
     mapCountries.put("Northern Mariana Islands", "MP");
     mapCountries.put("Martinique (French)", "MQ");
     mapCountries.put("Mauritania", "MR");
     mapCountries.put("Montserrat", "MS");
     mapCountries.put("Malta", "MT");
     mapCountries.put("Mauritius", "MU");
     mapCountries.put("Maldives", "MV");
     mapCountries.put("Malawi", "MW");
     mapCountries.put("Mexico", "MX");
     mapCountries.put("Malaysia", "MY");
     mapCountries.put("Mozambique", "MZ");
     mapCountries.put("Namibia", "NA");
     mapCountries.put("New Caledonia (French)", "NC");
     mapCountries.put("Niger", "NE");
     mapCountries.put("Norfolk Island", "NF");
     mapCountries.put("Nigeria", "NG");
     mapCountries.put("Nicaragua", "NI");
     mapCountries.put("Netherlands", "NL");
     mapCountries.put("Norway", "NO");
     mapCountries.put("Nepal", "NP");
     mapCountries.put("Nauru", "NR");
     mapCountries.put("Neutral Zone", "NT");
     mapCountries.put("Niue", "NU");
     mapCountries.put("New Zealand", "NZ");
     mapCountries.put("Oman", "OM");
     mapCountries.put("Panama", "PA");
     mapCountries.put("Peru", "PE");
     mapCountries.put("Polynesia (French)", "PF");
     mapCountries.put("Papua New Guinea", "PG");
     mapCountries.put("Philippines", "PH");
     mapCountries.put("Pakistan", "PK");
     mapCountries.put("Poland", "PL");
     mapCountries.put("Saint Pierre And Miquelon", "PM");
     mapCountries.put("Pitcairn Island", "PN");
     mapCountries.put("Puerto Rico", "PR");
     mapCountries.put("Portugal", "PT");
     mapCountries.put("Palau", "PW");
     mapCountries.put("Paraguay", "PY");
     mapCountries.put("Qatar", "QA");
     mapCountries.put("Reunion (French)", "RE");
     mapCountries.put("Romania", "RO");
     mapCountries.put("Russian Federation", "RU");
     mapCountries.put("Rwanda", "RW");
     mapCountries.put("Saudi Arabia", "SA");
     mapCountries.put("Solomon Islands", "SB");
     mapCountries.put("Seychelles", "SC");
     mapCountries.put("Sudan", "SD");
     mapCountries.put("Sweden", "SE");
     mapCountries.put("Singapore", "SG");
     mapCountries.put("Saint Helena", "SH");
     mapCountries.put("Slovenia", "SI");
     mapCountries.put("Svalbard And Jan Mayen Islands", "SJ");
     mapCountries.put("Slovak Republic", "SK");
     mapCountries.put("Sierra Leone", "SL");
     mapCountries.put("San Marino", "SM");
     mapCountries.put("Senegal", "SN");
     mapCountries.put("Somalia", "SO");
     mapCountries.put("Suriname", "SR");
     mapCountries.put("Saint Tome (Sao Tome) And Principe", "ST");
     mapCountries.put("Former USSR", "SU");
     mapCountries.put("El Salvador", "SV");
     mapCountries.put("Syria", "SY");
     mapCountries.put("Swaziland", "SZ");
     mapCountries.put("Turks And Caicos Islands", "TC");
     mapCountries.put("Chad", "TD");
     mapCountries.put("French Southern Territories", "TF");
     mapCountries.put("Togo", "TG");
     mapCountries.put("Thailand", "TH");
     mapCountries.put("Tadjikistan", "TJ");
     mapCountries.put("Tokelau", "TK");
     mapCountries.put("Turkmenistan", "TM");
     mapCountries.put("Tunisia", "TN");
     mapCountries.put("Tonga", "TO");
     mapCountries.put("East Timor", "TP");
     mapCountries.put("Turkey", "TR");
     mapCountries.put("Trinidad And Tobago", "TT");
     mapCountries.put("Tuvalu", "TV");
     mapCountries.put("Taiwan", "TW");
     mapCountries.put("Tanzania", "TZ");
     mapCountries.put("Ukraine", "UA");
     mapCountries.put("Uganda", "UG");
     mapCountries.put("United Kingdom", "UK");
     mapCountries.put("USA Minor Outlying Islands", "UM");
     mapCountries.put("United States", "US");
     mapCountries.put("Uruguay", "UY");
     mapCountries.put("Uzbekistan", "UZ");
     mapCountries.put("Holy See (Vatican City State)", "VA");
     mapCountries.put("Saint Vincent & Grenadines", "VC");
     mapCountries.put("Venezuela", "VE");
     mapCountries.put("Virgin Islands (British)", "VG");
     mapCountries.put("Virgin Islands (USA)", "VI");
     mapCountries.put("Vietnam", "VN");
     mapCountries.put("Vanuatu", "VU");
     mapCountries.put("Wallis And Futuna Islands", "WF");
     mapCountries.put("Samoa", "WS");
     mapCountries.put("Yemen", "YE");
     mapCountries.put("Mayotte", "YT");
     mapCountries.put("Yugoslavia", "YU");
     mapCountries.put("South Africa", "ZA");
     mapCountries.put("Zambia", "ZM");
     mapCountries.put("Zaire", "ZR");
     mapCountries.put("Zimbabwe", "ZW");
        mapCodes.put("AD", "Andorra, Principality Of");
        mapCodes.put("AE", "United Arab Emirates");
        mapCodes.put("AF", "Afghanistan, Islamic State Of");
        mapCodes.put("AG", "Antigua And Barbuda");
        mapCodes.put("AI", "Anguilla");
        mapCodes.put("AL", "Albania");
        mapCodes.put("AM", "Armenia");
        mapCodes.put("AN", "Netherlands Antilles");
        mapCodes.put("AO", "Angola");
        mapCodes.put("AQ", "Antarctica");
        mapCodes.put("AR", "Argentina");
        mapCodes.put("AS", "American Samoa");
        mapCodes.put("AT", "Austria");
        mapCodes.put("AU", "Australia");
        mapCodes.put("AW", "Aruba");
        mapCodes.put("AZ", "Azerbaidjan");
        mapCodes.put("BA", "Bosnia-Herzegovina");
        mapCodes.put("BB", "Barbados");
        mapCodes.put("BD", "Bangladesh");
        mapCodes.put("BE", "Belgium");
        mapCodes.put("BF", "Burkina Faso");
        mapCodes.put("BG", "Bulgaria");
        mapCodes.put("BH", "Bahrain");
        mapCodes.put("BI", "Burundi");
        mapCodes.put("BJ", "Benin");
        mapCodes.put("BM", "Bermuda");
        mapCodes.put("BN", "Brunei Darussalam");
        mapCodes.put("BO", "Bolivia");
        mapCodes.put("BR", "Brazil");
        mapCodes.put("BS", "Bahamas");
        mapCodes.put("BT", "Bhutan");
        mapCodes.put("BV", "Bouvet Island");
        mapCodes.put("BW", "Botswana");
        mapCodes.put("BY", "Belarus");
        mapCodes.put("BZ", "Belize");
        mapCodes.put("CA", "Canada");
        mapCodes.put("CC", "Cocos (Keeling) Islands");
        mapCodes.put("CF", "Central African Republic");
        mapCodes.put("CD", "Congo, The Democratic Republic Of The");
        mapCodes.put("CG", "Congo");
        mapCodes.put("CH", "Switzerland");
        mapCodes.put("CI", "Ivory Coast (Cote D'Ivoire)");
        mapCodes.put("CK", "Cook Islands");
        mapCodes.put("CL", "Chile");
        mapCodes.put("CM", "Cameroon");
        mapCodes.put("CN", "China");
        mapCodes.put("CO", "Colombia");
        mapCodes.put("CR", "Costa Rica");
        mapCodes.put("CS", "Former Czechoslovakia");
        mapCodes.put("CU", "Cuba");
        mapCodes.put("CV", "Cape Verde");
        mapCodes.put("CX", "Christmas Island");
        mapCodes.put("CY", "Cyprus");
        mapCodes.put("CZ", "Czech Republic");
        mapCodes.put("DE", "Germany");
        mapCodes.put("DJ", "Djibouti");
        mapCodes.put("DK", "Denmark");
        mapCodes.put("DM", "Dominica");
        mapCodes.put("DO", "Dominican Republic");
        mapCodes.put("DZ", "Algeria");
        mapCodes.put("EC", "Ecuador");
        mapCodes.put("EE", "Estonia");
        mapCodes.put("EG", "Egypt");
        mapCodes.put("EH", "Western Sahara");
        mapCodes.put("ER", "Eritrea");
        mapCodes.put("ES", "Spain");
        mapCodes.put("ET", "Ethiopia");
        mapCodes.put("FI", "Finland");
        mapCodes.put("FJ", "Fiji");
        mapCodes.put("FK", "Falkland Islands");
        mapCodes.put("FM", "Micronesia");
        mapCodes.put("FO", "Faroe Islands");
        mapCodes.put("FR", "France");
        mapCodes.put("FX", "France (European Territory)");
        mapCodes.put("GA", "Gabon");
        mapCodes.put("UK", "Great Britain");
        mapCodes.put("GD", "Grenada");
        mapCodes.put("GE", "Georgia");
        mapCodes.put("GF", "French Guyana");
        mapCodes.put("GH", "Ghana");
        mapCodes.put("GI", "Gibraltar");
        mapCodes.put("GL", "Greenland");
        mapCodes.put("GM", "Gambia");
        mapCodes.put("GN", "Guinea");
        mapCodes.put("GP", "Guadeloupe (French)");
        mapCodes.put("GQ", "Equatorial Guinea");
        mapCodes.put("GR", "Greece");
        mapCodes.put("GS", "S. Georgia & S. Sandwich Isls.");
        mapCodes.put("GT", "Guatemala");
        mapCodes.put("GU", "Guam (USA)");
        mapCodes.put("GW", "Guinea Bissau");
        mapCodes.put("GY", "Guyana");
        mapCodes.put("HK", "Hong Kong");
        mapCodes.put("HM", "Heard And McDonald Islands");
        mapCodes.put("HN", "Honduras");
        mapCodes.put("HR", "Croatia");
        mapCodes.put("HT", "Haiti");
        mapCodes.put("HU", "Hungary");
        mapCodes.put("ID", "Indonesia");
        mapCodes.put("IE", "Ireland");
        mapCodes.put("IL", "Israel");
        mapCodes.put("IN", "India");
        mapCodes.put("IO", "British Indian Ocean Territory");
        mapCodes.put("IQ", "Iraq");
        mapCodes.put("IR", "Iran");
        mapCodes.put("IS", "Iceland");
        mapCodes.put("IT", "Italy");
        mapCodes.put("JM", "Jamaica");
        mapCodes.put("JO", "Jordan");
        mapCodes.put("JP", "Japan");
        mapCodes.put("KE", "Kenya");
        mapCodes.put("KG", "Kyrgyz Republic (Kyrgyzstan)");
        mapCodes.put("KH", "Cambodia, Kingdom Of");
        mapCodes.put("KI", "Kiribati");
        mapCodes.put("KM", "Comoros");
        mapCodes.put("KN", "Saint Kitts & Nevis Anguilla");
        mapCodes.put("KP", "North Korea");
        mapCodes.put("KR", "South Korea");
        mapCodes.put("KW", "Kuwait");
        mapCodes.put("KY", "Cayman Islands");
        mapCodes.put("KZ", "Kazakhstan");
        mapCodes.put("LA", "Laos");
        mapCodes.put("LB", "Lebanon");
        mapCodes.put("LC", "Saint Lucia");
        mapCodes.put("LI", "Liechtenstein");
        mapCodes.put("LK", "Sri Lanka");
        mapCodes.put("LR", "Liberia");
        mapCodes.put("LS", "Lesotho");
        mapCodes.put("LT", "Lithuania");
        mapCodes.put("LU", "Luxembourg");
        mapCodes.put("LV", "Latvia");
        mapCodes.put("LY", "Libya");
        mapCodes.put("MA", "Morocco");
        mapCodes.put("MC", "Monaco");
        mapCodes.put("MD", "Moldavia");
        mapCodes.put("MG", "Madagascar");
        mapCodes.put("MH", "Marshall Islands");
        mapCodes.put("MK", "Macedonia");
        mapCodes.put("ML", "Mali");
        mapCodes.put("MM", "Myanmar");
        mapCodes.put("MN", "Mongolia");
        mapCodes.put("MO", "Macau");
        mapCodes.put("MP", "Northern Mariana Islands");
        mapCodes.put("MQ", "Martinique (French)");
        mapCodes.put("MR", "Mauritania");
        mapCodes.put("MS", "Montserrat");
        mapCodes.put("MT", "Malta");
        mapCodes.put("MU", "Mauritius");
        mapCodes.put("MV", "Maldives");
        mapCodes.put("MW", "Malawi");
        mapCodes.put("MX", "Mexico");
        mapCodes.put("MY", "Malaysia");
        mapCodes.put("MZ", "Mozambique");
        mapCodes.put("NA", "Namibia");
        mapCodes.put("NC", "New Caledonia (French)");
        mapCodes.put("NE", "Niger");
        mapCodes.put("NF", "Norfolk Island");
        mapCodes.put("NG", "Nigeria");
        mapCodes.put("NI", "Nicaragua");
        mapCodes.put("NL", "Netherlands");
        mapCodes.put("NO", "Norway");
        mapCodes.put("NP", "Nepal");
        mapCodes.put("NR", "Nauru");
        mapCodes.put("NT", "Neutral Zone");
        mapCodes.put("NU", "Niue");
        mapCodes.put("NZ", "New Zealand");
        mapCodes.put("OM", "Oman");
        mapCodes.put("PA", "Panama");
        mapCodes.put("PE", "Peru");
        mapCodes.put("PF", "Polynesia (French)");
        mapCodes.put("PG", "Papua New Guinea");
        mapCodes.put("PH", "Philippines");
        mapCodes.put("PK", "Pakistan");
        mapCodes.put("PL", "Poland");
        mapCodes.put("PM", "Saint Pierre And Miquelon");
        mapCodes.put("PN", "Pitcairn Island");
        mapCodes.put("PR", "Puerto Rico");
        mapCodes.put("PT", "Portugal");
        mapCodes.put("PW", "Palau");
        mapCodes.put("PY", "Paraguay");
        mapCodes.put("QA", "Qatar");
        mapCodes.put("RE", "Reunion (French)");
        mapCodes.put("RO", "Romania");
        mapCodes.put("RU", "Russian Federation");
        mapCodes.put("RW", "Rwanda");
        mapCodes.put("SA", "Saudi Arabia");
        mapCodes.put("SB", "Solomon Islands");
        mapCodes.put("SC", "Seychelles");
        mapCodes.put("SD", "Sudan");
        mapCodes.put("SE", "Sweden");
        mapCodes.put("SG", "Singapore");
        mapCodes.put("SH", "Saint Helena");
        mapCodes.put("SI", "Slovenia");
        mapCodes.put("SJ", "Svalbard And Jan Mayen Islands");
        mapCodes.put("SK", "Slovak Republic");
        mapCodes.put("SL", "Sierra Leone");
        mapCodes.put("SM", "San Marino");
        mapCodes.put("SN", "Senegal");
        mapCodes.put("SO", "Somalia");
        mapCodes.put("SR", "Suriname");
        mapCodes.put("ST", "Saint Tome (Sao Tome) And Principe");
        mapCodes.put("SU", "Former USSR");
        mapCodes.put("SV", "El Salvador");
        mapCodes.put("SY", "Syria");
        mapCodes.put("SZ", "Swaziland");
        mapCodes.put("TC", "Turks And Caicos Islands");
        mapCodes.put("TD", "Chad");
        mapCodes.put("TF", "French Southern Territories");
        mapCodes.put("TG", "Togo");
        mapCodes.put("TH", "Thailand");
        mapCodes.put("TJ", "Tadjikistan");
        mapCodes.put("TK", "Tokelau");
        mapCodes.put("TM", "Turkmenistan");
        mapCodes.put("TN", "Tunisia");
        mapCodes.put("TO", "Tonga");
        mapCodes.put("TP", "East Timor");
        mapCodes.put("TR", "Turkey");
        mapCodes.put("TT", "Trinidad And Tobago");
        mapCodes.put("TV", "Tuvalu");
        mapCodes.put("TW", "Taiwan");
        mapCodes.put("TZ", "Tanzania");
        mapCodes.put("UA", "Ukraine");
        mapCodes.put("UG", "Uganda");
        mapCodes.put("UK", "United Kingdom");
        mapCodes.put("UM", "USA Minor Outlying Islands");
        mapCodes.put("US", "United States");
        mapCodes.put("UY", "Uruguay");
        mapCodes.put("UZ", "Uzbekistan");
        mapCodes.put("VA", "Holy See (Vatican City State)");
        mapCodes.put("VC", "Saint Vincent & Grenadines");
        mapCodes.put("VE", "Venezuela");
        mapCodes.put("VG", "Virgin Islands (British)");
        mapCodes.put("VI", "Virgin Islands (USA)");
        mapCodes.put("VN", "Vietnam");
        mapCodes.put("VU", "Vanuatu");
        mapCodes.put("WF", "Wallis And Futuna Islands");
        mapCodes.put("WS", "Samoa");
        mapCodes.put("YE", "Yemen");
        mapCodes.put("YT", "Mayotte");
        mapCodes.put("YU", "Yugoslavia");
        mapCodes.put("ZA", "South Africa");
        mapCodes.put("ZM", "Zambia");
        mapCodes.put("ZR", "Zaire");
        mapCodes.put("ZW", "Zimbabwe");

    }

    public String getCountry(String code) {
        String codeFound = mapCodes.get(code);
        if (codeFound == null) {
            codeFound = "Uruguay";
        }
        return codeFound;
    }
     public String getCode(String country){
     String countryFound = mapCountries.get(country);
     if(countryFound==null){
             countryFound="UY";
     }
     return countryFound;
     }
}
