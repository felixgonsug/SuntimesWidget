/**
    Copyright (C) 2014 Forrest Guice
    This file is part of SuntimesWidget.

    SuntimesWidget is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SuntimesWidget is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SuntimesWidget.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package com.forrestguice.suntimeswidget.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.forrestguice.suntimeswidget.R;
import com.forrestguice.suntimeswidget.calculator.SuntimesCalculatorDescriptor;
import com.forrestguice.suntimeswidget.calculator.SuntimesCalculatorFactory;

public class SuntimesWidgetSettings
{
    private static final String PREFS_WIDGET = "com.forrestguice.suntimeswidget";

    public static final String PREF_PREFIX_KEY = "appwidget_";
    public static final String PREF_PREFIX_KEY_APPEARANCE = "_appearance_";
    public static final String PREF_PREFIX_KEY_GENERAL = "_general_";
    public static final String PREF_PREFIX_KEY_LOCATION = "_location_";
    public static final String PREF_PREFIX_KEY_TIMEZONE = "_timezone_";

    public static final String PREF_KEY_GENERAL_CALCULATOR = "calculator";
    public static final String PREF_DEF_GENERAL_CALCULATOR = "any";

    public static final String PREF_KEY_APPEARANCE_THEME = "theme";
    public static final String PREF_DEF_APPEARANCE_THEME = SuntimesWidgetThemes.THEMEDEF_DEF_NAME;

    public static final String PREF_KEY_APPEARANCE_SHOWTITLE = "showtitle";
    public static final boolean PREF_DEF_APPEARANCE_SHOWTITLE = true;

    private static final String PREF_KEY_APPEARANCE_TITLETEXT = "titletext";
    private static final String PREF_DEF_APPEARANCE_TITLETEXT = "%M";

    public static final String PREF_KEY_GENERAL_TIMEMODE = "timemode";
    public static final TimeMode PREF_DEF_GENERAL_TIMEMODE = TimeMode.OFFICIAL;

    public static final String PREF_KEY_GENERAL_COMPAREMODE = "comparemode";
    public static final CompareMode PREF_DEF_GENERAL_COMPAREMODE = CompareMode.TOMORROW;

    public static final String PREF_KEY_LOCATION_MODE = "locationMode";
    public static final LocationMode PREF_DEF_LOCATION_MODE = LocationMode.CUSTOM_LOCATION;

    public static final String PREF_KEY_LOCATION_LONGITUDE = "longitude";
    public static final String PREF_DEF_LOCATION_LONGITUDE = "-112.4677778";

    public static final String PREF_KEY_LOCATION_LATITUDE = "latitude";
    public static final String PREF_DEF_LOCATION_LATITUDE = "34.54";

    public static final String PREF_KEY_TIMEZONE_MODE = "timezoneMode";
    public static final TimezoneMode PREF_DEF_TIMEZONE_MODE = TimezoneMode.CUSTOM_TIMEZONE;

    public static final String PREF_KEY_TIMEZONE_CUSTOM = "timezone";
    public static final String PREF_DEF_TIMEZONE_CUSTOM = "US/Arizona";


    /**
     * Calculator
     */

    /**
     * TimezoneMode
     */
    public static enum TimezoneMode
    {
        CURRENT_TIMEZONE("Current Timezone"),
        CUSTOM_TIMEZONE("Custom Timezone");

        private String displayString;

        private TimezoneMode(String displayString)
        {
            this.displayString = displayString;
        }

        public String toString()
        {
            return displayString;
        }

        public String getDisplayString()
        {
            return displayString;
        }

        public void setDisplayString( String displayString )
        {
            this.displayString = displayString;
        }

        public static void initDisplayStrings( Context context )
        {
            CURRENT_TIMEZONE.setDisplayString(context.getString(R.string.timezoneMode_current));
            CUSTOM_TIMEZONE.setDisplayString(context.getString(R.string.timezoneMode_current));
        }
    }

    /**
     * LocationMode
     */
    public static enum LocationMode
    {
        CURRENT_LOCATION("Current Location"),
        CUSTOM_LOCATION("Custom Location");

        private String displayString;

        private LocationMode(String displayString)
        {
            this.displayString = displayString;
        }

        public String toString()
        {
            return displayString;
        }

        public String getDisplayString()
        {
            return displayString;
        }

        public void setDisplayString( String displayString )
        {
            this.displayString = displayString;
        }

        public static void initDisplayStrings( Context context )
        {
            CURRENT_LOCATION.setDisplayString(context.getString(R.string.locationMode_current));
            CUSTOM_LOCATION.setDisplayString(context.getString(R.string.locationMode_custom));
        }
    }

    public static class Location
    {
        String latitude;
        String longitude;

        public Location( String latitude, String longitude )
        {
            this.latitude = latitude;
            this.longitude = longitude;
        }


        public String getLatitude()
        {
            return latitude;
        }

        public String getLongitude()
        {
            return longitude;
        }
    }

    public static enum CompareMode
    {
        YESTERDAY("Yesterday"),
        TOMORROW("Tomorrow");

        private String displayString;

        private CompareMode( String displayString )
        {
            this.displayString = displayString;
        }

        public String getDisplayString()
        {
            return displayString;
        }

        public void setDisplayString( String displayString )
        {
            this.displayString = displayString;
        }

        public String toString()
        {
            return displayString;
        }

        public static void initDisplayStrings( Context context )
        {
            YESTERDAY.setDisplayString( context.getString(R.string.compareMode_yesterday) );
            TOMORROW.setDisplayString( context.getString(R.string.compareMode_tomorrow) );
        }
    }

    /**
     * TimeMode
     */
    public static enum TimeMode
    {
        OFFICIAL("Actual", "Actual Time"),
        NAUTICAL("Nautical", "Nautical Twilight"),
        CIVIL("Civil", "Civil Twilight"),
        ASTRONOMICAL("Astronomical", "Astronomical Twilight");

        public static boolean shortDisplayStrings = false;
        private String longDisplayString;
        private String shortDisplayString;

        private TimeMode(String shortDisplayString, String longDisplayString)
        {
            this.shortDisplayString = shortDisplayString;
            this.longDisplayString = longDisplayString;
        }

        public String toString()
        {
            if (shortDisplayStrings)
            {
                return shortDisplayString;

            } else {
                return longDisplayString;
            }
        }

        public String getShortDisplayString()
        {
            return shortDisplayString;
        }

        public String getLongDisplayString()
        {
            return longDisplayString;
        }

        public void setDisplayStrings(String shortDisplayString, String longDisplayString)
        {
            this.shortDisplayString = shortDisplayString;
            this.longDisplayString = longDisplayString;
        }

        public static void initDisplayStrings( Context context )
        {
            OFFICIAL.setDisplayStrings( context.getString(R.string.timeMode_official_short),
                    context.getString(R.string.timeMode_official) );

            NAUTICAL.setDisplayStrings( context.getString(R.string.timeMode_nautical_short),
                    context.getString(R.string.timeMode_nautical));

            CIVIL.setDisplayStrings( context.getString(R.string.timeMode_civil_short),
                    context.getString(R.string.timeMode_civil) );

            ASTRONOMICAL.setDisplayStrings( context.getString(R.string.timeMode_astronomical_short),
                    context.getString(R.string.timeMode_astronomical) );
        }
    }


    public static void saveThemePref(Context context, int appWidgetId, String themeName)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.putString(prefs_prefix + PREF_KEY_APPEARANCE_THEME, themeName);
        prefs.commit();
    }
    public static SuntimesWidgetTheme loadThemePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        String themeName = prefs.getString(prefs_prefix + PREF_KEY_APPEARANCE_THEME, PREF_DEF_APPEARANCE_THEME);

        SuntimesWidgetTheme theme = SuntimesWidgetThemes.loadTheme(context, themeName);
        return theme;
    }
    public static void deleteThemePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.remove(prefs_prefix + PREF_KEY_APPEARANCE_THEME);
        prefs.commit();
    }


    public static void saveCalculatorModePref(Context context, int appWidgetId, SuntimesCalculatorDescriptor mode)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.putString(prefs_prefix + PREF_KEY_GENERAL_CALCULATOR, mode.name());
        prefs.commit();
    }
    public static SuntimesCalculatorDescriptor loadCalculatorModePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        String modeString = prefs.getString(prefs_prefix + PREF_KEY_GENERAL_CALCULATOR, PREF_DEF_GENERAL_CALCULATOR);

        SuntimesCalculatorDescriptor calculatorMode = null;
        try
        {
            calculatorMode = SuntimesCalculatorDescriptor.valueOf(modeString);

        } catch (IllegalArgumentException e) {
            Log.e("loadCalculatorModePref", e.toString() + " ... It looks like " + modeString + " isn't in our list of calculators.");
            // TODO: handle this better. right now it allows this function to return a null, which triggers NullPointerExceptions later
            // ... what is the right course of action? either instantiate a default (that couples us to that third party code) or ...? our widget doesn't currently have an error state
        }
        return calculatorMode;
    }
    public static void deleteCalculatorModePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.remove(prefs_prefix + PREF_KEY_GENERAL_CALCULATOR);
        prefs.commit();
    }


    public static void saveShowTitlePref(Context context, int appWidgetId, boolean showTitle)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.putBoolean(prefs_prefix + PREF_KEY_APPEARANCE_SHOWTITLE, showTitle);
        prefs.commit();
    }
    public static boolean loadShowTitlePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        return prefs.getBoolean(prefs_prefix + PREF_KEY_APPEARANCE_SHOWTITLE, PREF_DEF_APPEARANCE_SHOWTITLE);
    }
    public static void deleteShowTitlePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.remove(prefs_prefix + PREF_KEY_APPEARANCE_SHOWTITLE);
        prefs.commit();
    }


    public static void saveTitleTextPref(Context context, int appWidgetId, String titleText)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.putString(prefs_prefix + PREF_KEY_APPEARANCE_TITLETEXT, titleText);
        prefs.commit();
    }
   public static String loadTitleTextPref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        return prefs.getString(prefs_prefix + PREF_KEY_APPEARANCE_TITLETEXT, PREF_DEF_APPEARANCE_TITLETEXT);
    }
    public static void deleteTitleTextPref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_APPEARANCE;
        prefs.remove(prefs_prefix + PREF_KEY_APPEARANCE_TITLETEXT);
        prefs.commit();
    }


    public static void saveTimeModePref(Context context, int appWidgetId, SuntimesWidgetSettings.TimeMode mode)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.putString(prefs_prefix + PREF_KEY_GENERAL_TIMEMODE, mode.name());
        prefs.commit();
    }
    public static SuntimesWidgetSettings.TimeMode loadTimeModePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        String modeString = prefs.getString(prefs_prefix + PREF_KEY_GENERAL_TIMEMODE, PREF_DEF_GENERAL_TIMEMODE.name());

        TimeMode timeMode;
        try
        {
            timeMode = SuntimesWidgetSettings.TimeMode.valueOf(modeString);

        } catch (IllegalArgumentException e) {
            timeMode = PREF_DEF_GENERAL_TIMEMODE;
        }
        return timeMode;
    }
    public static void deleteTimeModePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.remove(prefs_prefix + PREF_KEY_GENERAL_TIMEMODE);
        prefs.commit();
    }


    public static void saveLocationModePref(Context context, int appWidgetId, SuntimesWidgetSettings.LocationMode mode)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        prefs.putString(prefs_prefix + PREF_KEY_LOCATION_MODE, mode.name());
        prefs.commit();
    }
    public static SuntimesWidgetSettings.LocationMode loadLocationModePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        String modeString = prefs.getString(prefs_prefix + PREF_KEY_LOCATION_MODE, PREF_DEF_LOCATION_MODE.name());

        LocationMode locationMode;
        try
        {
            locationMode = SuntimesWidgetSettings.LocationMode.valueOf(modeString);

        } catch (IllegalArgumentException e) {
            locationMode = PREF_DEF_LOCATION_MODE;
        }
        return locationMode;
    }
    public static void deleteLocationModePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        prefs.remove(prefs_prefix + PREF_KEY_LOCATION_MODE);
        prefs.commit();
    }


    public static void saveTimezoneModePref(Context context, int appWidgetId, SuntimesWidgetSettings.TimezoneMode mode)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        prefs.putString(prefs_prefix + PREF_KEY_TIMEZONE_MODE, mode.name());
        prefs.commit();
    }
    public static SuntimesWidgetSettings.TimezoneMode loadTimezoneModePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        String modeString = prefs.getString(prefs_prefix + PREF_KEY_TIMEZONE_MODE, PREF_DEF_TIMEZONE_MODE.name());

        TimezoneMode timezoneMode;
        try
        {
            timezoneMode = SuntimesWidgetSettings.TimezoneMode.valueOf(modeString);

        } catch (IllegalArgumentException e) {
            timezoneMode = PREF_DEF_TIMEZONE_MODE;
        }
        return timezoneMode;
    }
    public static void deleteTimezoneModePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        prefs.remove(prefs_prefix + PREF_KEY_TIMEZONE_MODE);
        prefs.commit();
    }


    public static void saveLocationPref(Context context, int appWidgetId, Location location)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        prefs.putString(prefs_prefix + PREF_KEY_LOCATION_LONGITUDE, location.getLongitude());
        prefs.putString(prefs_prefix + PREF_KEY_LOCATION_LATITUDE, location.getLatitude());
        prefs.commit();
    }
    public static Location loadLocationPref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        String lonString = prefs.getString(prefs_prefix + PREF_KEY_LOCATION_LONGITUDE, PREF_DEF_LOCATION_LONGITUDE);
        String latString = prefs.getString(prefs_prefix + PREF_KEY_LOCATION_LATITUDE, PREF_DEF_LOCATION_LATITUDE);
        return new Location(latString, lonString);
    }
    public static void deleteLocationPref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_LOCATION;
        prefs.remove(prefs_prefix + PREF_KEY_LOCATION_LONGITUDE);
        prefs.remove(prefs_prefix + PREF_KEY_LOCATION_LATITUDE);
        prefs.commit();
    }


    public static void saveTimezonePref(Context context, int appWidgetId, String timezone)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        prefs.putString(prefs_prefix + PREF_KEY_TIMEZONE_CUSTOM, timezone);
        prefs.commit();
    }
    public static String loadTimezonePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        return prefs.getString(prefs_prefix + PREF_KEY_TIMEZONE_CUSTOM, PREF_DEF_TIMEZONE_CUSTOM);
    }
    public static void deleteTimezonePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_TIMEZONE;
        prefs.remove(prefs_prefix + PREF_KEY_TIMEZONE_CUSTOM);
        prefs.commit();
    }


    public static void saveCompareModePref(Context context, int appWidgetId, SuntimesWidgetSettings.CompareMode mode)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.putString(prefs_prefix + PREF_KEY_GENERAL_COMPAREMODE, mode.name());
        prefs.commit();
    }
    public static SuntimesWidgetSettings.CompareMode loadCompareModePref(Context context, int appWidgetId)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_WIDGET, 0);
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        String modeString = prefs.getString(prefs_prefix + PREF_KEY_GENERAL_COMPAREMODE, PREF_DEF_GENERAL_COMPAREMODE.name());

        CompareMode compareMode;
        try
        {
            compareMode = SuntimesWidgetSettings.CompareMode.valueOf(modeString);

        } catch (IllegalArgumentException e) {
            compareMode = PREF_DEF_GENERAL_COMPAREMODE;
        }
        return compareMode;
    }
    public static void deleteCompareModePref(Context context, int appWidgetId)
    {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_WIDGET, 0).edit();
        String prefs_prefix = PREF_PREFIX_KEY + appWidgetId + PREF_PREFIX_KEY_GENERAL;
        prefs.remove(prefs_prefix + PREF_KEY_GENERAL_COMPAREMODE);
        prefs.commit();
    }


    public static void deletePrefs(Context context, int appWidgetId)
    {
        deleteThemePref(context, appWidgetId);
        deleteShowTitlePref(context, appWidgetId);
        deleteTitleTextPref(context, appWidgetId);

        deleteCalculatorModePref(context, appWidgetId);
        deleteTimeModePref(context, appWidgetId);
        deleteCompareModePref(context, appWidgetId);

        deleteLocationModePref(context, appWidgetId);
        deleteLocationPref(context, appWidgetId);

        deleteTimezoneModePref(context, appWidgetId);
        deleteTimezonePref(context, appWidgetId);
    }

}