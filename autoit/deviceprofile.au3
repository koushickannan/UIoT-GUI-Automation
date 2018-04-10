Global $Var_1 = @ScriptDir
Global $dir_parent = StringLeft($Var_1,StringInStr($Var_1,"\",0,-1)-1)
ControlFocus("Open", "", "Edit1")
ControlSetText("Open", "", "Edit1",$dir_parent & "\testdata\deviceprofile.xml")
#Send($dir_parent & "\testdata\deviceprofile.xml")
Send("{ENTER}")
