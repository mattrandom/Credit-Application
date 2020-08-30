How to use an application? You can use this application in two ways.

<b>I. You need to set [Main DEFAULT] in EDIT CONFIGURATIONS:</b>
  - ENVIRONMENT VARIABLES should be set as: API_TOKEN=f106fee1-13c1-4af6-bc9c-a9bd405314c3
If you add a new credit application in .JSON format to the root directory (${OUTPUT_PATH}), which is the project root path by default specified as System.getProperty("user.dir"), 
then you should get processed credit application stored as a file with randomUUID.dat in one of the following directories:
  - ${OUTPUT_PATH}/Natural-Person/${PESEL}/${ID}.dat
  - ${OUTPUT_PATH}/Self-Employed/${NIP} or {REGON}/${ID}.dat
  
  This mode is responsible for reading & processing credit applications.

<b>II. You need to set [Main BROWSE] in EDIT CONFIGURATIONS:</b>
  - ENVIRONMENT VARIABLES should be set as: API_TOKEN=f106fee1-13c1-4af6-bc9c-a9bd405314c3
  - PROGRAM ARGUMENTS should be set as: 
    - ${ID} N-${PESEL} in case of reading and printing previously generated applications with parameters set for NaturalPerson in *.JSON file,
    - ${ID} S-${NIP or REGON} in case of reading and printing previously generated applications with parameters set for SelfEmployed in *.JSON file.
    
    E.g. 8099efdf-3db2-491b-904c-5eec17d944e9 N-11122233345
