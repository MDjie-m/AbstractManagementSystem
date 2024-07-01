/**
 * CertificateRequestVIP_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package info.vidc.www.certificate.operation;

public interface CertificateRequestVIP_PortType extends java.rmi.Remote {
    public info.vidc.www.certificate.operation.CertificateInfo[] queryCertificateSingle(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.CertificateInfo[] queryHisByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String startTime, java.lang.String endTime, java.lang.String applicType, int status, int pagesite, int pageSize) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.OperateResult uploadOverTime_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException;
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.OperateResult uploadDelete_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.OperateResult uploadUpdate_EntEX(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException;
    public byte[] getAfficheZC(java.lang.String username, java.lang.String password, java.lang.String pc, java.lang.String cph, java.lang.String clxh) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.OperateResult uploadInser_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String ukey) throws java.rmi.RemoteException;
    public byte[] getAfficheDP(java.lang.String username, java.lang.String password, java.lang.String pc, java.lang.String cph, java.lang.String dpxh) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.CertificateInfo[] queryOnWayByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String startTime, java.lang.String endTime, java.lang.String applicType, java.lang.String status, int pagesite, int pageSize) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.CertificateInfo[] queryByCondition(java.lang.String username, java.lang.String password, java.lang.String wzhgzbh, java.lang.String clsbdh, java.lang.String clxh, java.lang.String status, java.lang.String startTime, java.lang.String endTime, int pagesite, int pageSize) throws java.rmi.RemoteException;
    public info.vidc.www.certificate.operation.OperateResult uploadUpdate_Ent(java.lang.String username, java.lang.String password, info.vidc.www.certificate.operation.CertificateInfo[] certificateInfos, java.lang.String memo, java.lang.String ukey) throws java.rmi.RemoteException;
}
