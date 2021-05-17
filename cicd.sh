mvn clean test > salida.txt
resultBase=$(cat salida.txt | grep -m1 "Tests run")
rm -rf salida.txt
echo $resultBase
numberOfErrors=$(echo $resultBase | cut -d "," -f2 | cut -d ":" -f2 )

if [ "$numberOfErrors" -eq "0" ]; then
    echo "Selenium test has successfully run";
    exit 0;
else
    echo "Selenium Tests ERRORs, #$numberOfErrors";
    exit 1;
fi
