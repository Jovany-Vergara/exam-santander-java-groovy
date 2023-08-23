"tesseract test.jpeg output".execute()
def pattern = /([A-Z][AEIOUX][A-Z]{2}\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/
File file = new File("output.txt");
String curp = "";
file.text.eachLine { line ->
  listInList = line;
  listInList.toString().split(" ").each { word ->
    if(word ==~ pattern){
      curp = word
    }
  };
}
println "CURP: ${curp}"
