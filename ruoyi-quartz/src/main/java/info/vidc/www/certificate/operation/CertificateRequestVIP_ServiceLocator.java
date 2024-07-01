/**
 * CertificateRequestVIP_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package info.vidc.www.certificate.operation;

public class CertificateRequestVIP_ServiceLocator extends org.apache.axis.client.Service implements info.vidc.www.certificate.operation.CertificateRequestVIP_Service {

    public CertificateRequestVIP_ServiceLocator() {
    }


    public CertificateRequestVIP_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CertificateRequestVIP_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CertificateRequestVIPServiceImplPort
    private java.lang.String CertificateRequestVIPServiceImplPort_address = "https://hgz.miit.gov.cn/enterprise/services/CertificateRequestVIPService";
    public java.lang.String getCertificateRequestVIPServiceImplPortAddress() {
        return CertificateRequestVIPServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CertificateRequestVIPServiceImplPortWSDDServiceName = "CertificateRequestVIPServiceImplPort";

    public java.lang.String getCertificateRequestVIPServiceImplPortWSDDServiceName() {
        return CertificateRequestVIPServiceImplPortWSDDServiceName;
    }

    public void setCertificateRequestVIPServiceImplPortWSDDServiceName(java.lang.String name) {
        CertificateRequestVIPServiceImplPortWSDDServiceName = name;
    }

    public info.vidc.www.certificate.operation.CertificateRequestVIP_PortType getCertificateRequestVIPServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CertificateRequestVIPServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCertificateRequestVIPServiceImplPort(endpoint);
    }

    public info.vidc.www.certificate.operation.CertificateRequestVIP_PortType getCertificateRequestVIPServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            info.vidc.www.certificate.operation.CertificateRequestVIPSoapBindingStub _stub = new info.vidc.www.certificate.operation.CertificateRequestVIPSoapBindingStub(portAddress, this);
            _stub.setPortName(getCertificateRequestVIPServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCertificateRequestVIPServiceImplPortEndpointAddress(java.lang.String address) {
        CertificateRequestVIPServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (info.vidc.www.certificate.operation.CertificateRequestVIP_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                info.vidc.www.certificate.operation.CertificateRequestVIPSoapBindingStub _stub = new info.vidc.www.certificate.operation.CertificateRequestVIPSoapBindingStub(new java.net.URL(CertificateRequestVIPServiceImplPort_address), this);
                _stub.setPortName(getCertificateRequestVIPServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CertificateRequestVIPServiceImplPort".equals(inputPortName)) {
            return getCertificateRequestVIPServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vidc.info/certificate/operation/", "CertificateRequestVIP");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vidc.info/certificate/operation/", "CertificateRequestVIPServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CertificateRequestVIPServiceImplPort".equals(portName)) {
            setCertificateRequestVIPServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
