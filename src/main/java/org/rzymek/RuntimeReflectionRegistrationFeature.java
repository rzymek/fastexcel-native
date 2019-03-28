package org.rzymek;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.Feature;
import org.graalvm.nativeimage.RuntimeReflection;

@AutomaticFeature
class RuntimeReflectionRegistrationFeature implements Feature {
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        try {
            String[] register = {
                    "com.sun.xml.internal.stream.XMLInputFactoryImpl",
                    "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl",
                    "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.SstDocumentImpl",
                    "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSstImpl",
                    "org.apache.xmlbeans.impl.values.XmlComplexContentImpl",
                    "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField"
            };
            for (String name : register) {
                RuntimeReflection.register(Class.forName(name));
                RuntimeReflection.register(Class.forName(name).getConstructors());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
