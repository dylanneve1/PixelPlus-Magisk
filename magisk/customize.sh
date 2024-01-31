# Variables
gms=/data/data/com.google.android.gms/databases/phenotype.db
sqlite=$MODPATH/addon/sqlite3

chmod 0755 $MODPATH/addon/*

gacc="$("$sqlite" "$gms" "SELECT DISTINCT(user) FROM Flags WHERE user != '';")"

LAUNCHER_FLAGS="ENABLE_LONG_PRESS_NAV_HANDLE
ENABLE_LONG_PRESS_NAV_HANDLE_MPR
ENABLE_SETTINGS_OSE_CUSTOMIZATIONS
INVOKE_OMNI_LPH
INVOKE_OMNI_LPH_MPR
enable_one_search
enable_quick_launch_v3_aa
enable_quick_launch_v3_qsb
gboard_update_enter_key
long_press_home_button_to_search
long_press_home_button_to_search_mpr
press_hold_nav_handle_to_search
press_hold_nav_handle_to_search_mpr"

db_edit() {
    chgrp root $gms
    chown root $gms
    sleep .05
    type=$2
    val=$3
    name=$1
    shift
    shift
    shift
    for i in $@; do
        $sqlite $gms "DELETE FROM FlagOverrides WHERE packageName='$name' AND name='$i'"
        sleep .001
        $sqlite $gms "INSERT INTO FlagOverrides(packageName, user, name, flagType, $type, committed) VALUES('$name', '', '$i', 0, $val, 0)"
        sleep .001
        $sqlite $gms "INSERT INTO FlagOverrides(packageName, user, name, flagType, $type, committed) VALUES('$name', '', '$i', 0, $val, 1)"
        for j in $gacc; do
            j=${j/.db/}
            $sqlite $gms "INSERT INTO FlagOverrides(packageName, user, name, flagType, $type, committed) VALUES('$name', '$j', '$i', 0, $val, 0)"
            sleep .001
        done
    done
}

db_edit com.google.android.platform.launcher boolVal 1 $LAUNCHER_FLAGS
