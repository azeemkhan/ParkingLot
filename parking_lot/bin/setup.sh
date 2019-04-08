#!/usr/bin/env sh

main(){
	CurrentDir=( `pwd` )
	( cd ${CurrentDir}/../ &&  gradle clean distZip distTar installDist )
}
main $*
