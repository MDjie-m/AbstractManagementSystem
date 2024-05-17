package info.vidc.www.certificate.operation;

public class CertificateRequestVIPProxy implements info.vidc.www.certificate.operation.CertificateRequestVIP_PortType {
  private String _endpoint = null;
  private info.vidc.www.certificate.operation.CertificateRequestVIP_PortType certificateRequestVIP_PortType = null;
  
  public CertificateRequestVIPProxy() {
    _initCertificateRequestVIPProxy();
  }
  
  public CertificateRequestVIPProxy(String endpoint) {
    _endpoint = endpoint;
    _initCertificateRequestVIPProxy();
  }
  
  private void _initCertificateRequestVIPProxy() {
    try {
      certificateRequestVIP_PortType = (new info.vidc.www.certificate.operation.CertificateRequestVIP_ServiceLocator()).getCertificateRequestVIPServiceImplPort();
      if (certificateRequestVIP_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)certificateRequestVIP_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)certificateRequestVIP_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (certificateRequestVIP_PortType != null)
      ((javax.xml.rpc.Stub)certificateRequestVIP_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public info.vidc.www.certificate.operation.CertificateRequestVIP_PortType getCertificateRequestVIP_PortType() {
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType;
  }
  
  public info.vidc.www.certificate.operation.CertificateInfo[] queryCertificateSingle(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.queryCertificateSingle(username, password, wzhgzbh, clsbdh);
  }
  
  public info.vidc.www.certificate.operation.CertificateInfo[] queryHisByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String startTime, java.lang.String endTime, java.lang.String applicType, int status, int pagesite, int pageSize) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.queryHisByCondition(username, password, wzhgzbh, clsbdh, startTime, endTime, applicType, status, pagesite, pageSize);
  }
  
  public info.vidc.www.certificate.operation.OperateResult uploadOverTime_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.uploadOverTime_Ent(username, password, certificateInfos, memo, ukey);
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.helloWorld();
  }
  
  public info.vidc.www.certificate.operation.OperateResult uploadDelete_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.uploadDelete_Ent(username, password, certificateInfos, memo, ukey);
  }
  
  public info.vidc.www.certificate.operation.OperateResult uploadUpdate_EntEX(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.uploadUpdate_EntEX(username, password, certificateInfos, memo, ukey);
  }
  
  public byte[] getAfficheZC(java.lang.String username, java.lang.String password, java.lang.String pc, java.lang.String cph, java.lang.String clxh) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.getAfficheZC(username, password, pc, cph, clxh);
  }
  
  public info.vidc.www.certificate.operation.OperateResult uploadInser_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String ukey) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.uploadInser_Ent(username, password, certificateInfos, ukey);
  }
  
  public byte[] getAfficheDP(java.lang.String username, java.lang.String password, java.lang.String pc, java.lang.String cph, java.lang.String dpxh) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.getAfficheDP(username, password, pc, cph, dpxh);
  }
  
  public info.vidc.www.certificate.operation.CertificateInfo[] queryOnWayByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String startTime, java.lang.String endTime, java.lang.String applicType, java.lang.String status, int pagesite, int pageSize) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.queryOnWayByCondition(username, password, wzhgzbh, clsbdh, startTime, endTime, applicType, status, pagesite, pageSize);
  }
  
  public info.vidc.www.certificate.operation.CertificateInfo[] queryByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String clxh, java.lang.String status, java.lang.String startTime, java.lang.String endTime, int pagesite, int pageSize) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.queryByCondition(username, password, wzhgzbh, clsbdh, clxh, status, startTime, endTime, pagesite, pageSize);
  }
  
  public info.vidc.www.certificate.operation.OperateResult uploadUpdate_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException{
    if (certificateRequestVIP_PortType == null)
      _initCertificateRequestVIPProxy();
    return certificateRequestVIP_PortType.uploadUpdate_Ent(username, password, certificateInfos, memo, ukey);
  }
  
  
}