$appname = $args[0]

cd classes
java "$appname.Application" $args[1] $args[2]
cd ..