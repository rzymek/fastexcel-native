#!/bin/bash
set -euo pipefail
cd $(dirname "$0")

mvn package -DskipTests

time -p ${GRAALVM_HOME}/bin/native-image \
    -H:InitialCollectionPolicy='com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime' \
    -jar target/fastexcel-native-0-SNAPSHOT-jar-with-dependencies.jar target/fastexcel \
    -J-Djava.util.concurrent.ForkJoinPool.common.parallelism=1 -H:+PrintAnalysisCallTree -H:-AddAllCharsets \
    -H:EnableURLProtocols=http -H:NativeLinkerOption=-no-pie -H:-SpawnIsolates \
    -H:-JNI --no-server -H:-UseServiceLoaderFeature -H:+StackTrace

time -p ./target/fastexcel ./test.xlsx