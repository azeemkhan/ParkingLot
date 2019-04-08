#!/usr/bin/env sh

main(){
	CurrentDir=( `pwd` )
	( cd ${CurrentDir}/../ && gradle clean build )
}
main $*
