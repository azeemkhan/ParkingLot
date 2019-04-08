#!/usr/bin/env sh

main(){
    echo "=========Gradle build========="
    build $*
    echo "Build done====="

    CUR_DIR=( `pwd`)
    ARG=$1
    LIBS=${CUR_DIR}/../build/install/parking_lot/lib
    ( java -cp .:${LIBS}/* com.gojek.parkinglot.ParkingLotMainApplication ${ARG} )
    echo "Completed"
}

build(){
    CurrentDir=( `pwd` )
	( cd ${CurrentDir}/../ &&  gradle clean distZip distTar installDist )
}
main $*
