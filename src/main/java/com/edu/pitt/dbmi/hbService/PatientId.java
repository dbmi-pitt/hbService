package edu.pitt.dbmi.empi;

public class PatientId

{

String studyId;

String mpacIds;

String srcIds;

public PatientId()

{}

public PatientId(String studyId, String mpacIds, String srcIds)

{

this.studyId = studyId;

this.mpacIds = mpacIds;

this.srcIds = srcIds;

}

public void setStudyId(String studyId)

{

this.studyId = studyId;

}

public String getStudyId()

{

return studyId;

}

public void setMpacIds(String mpacIds)

{

this.mpacIds= mpacIds;

}

public String getMpacIds()

{

return mpacIds;

}

public void addMpacId(String mpacId)

{

this.mpacIds = this.mpacIds + ", " + mpacId;

}

public void setSrcIds(String srcIds)

{

this.srcIds = srcIds;

}

public String getSrcIds()

{

return srcIds;

}

public void addSrcId(String srcId)

{

this.srcIds = this.srcIds + ", " + srcId;

}

public String toString()

{

String str = "studyId: " + studyId + "\n";

str += "mpacIds: " + mpacIds + "\n";

str += "srcIds: " + srcIds + "\n";

str += "------------------------";

return str;

}

}
