package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.utils.PageUtils;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.request.PsyConsultReq;
import com.renxin.psychology.request.PsyConsultServeConfigReq;
import com.renxin.psychology.request.QueryListByTypeReq;
import com.renxin.psychology.service.IPsyConsultColumnService;
import com.renxin.psychology.service.IPsyConsultServeConfigService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.vo.PsyConsultColumnVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/consult")
public class PocketConsultantController extends BaseController
{
    @Resource
    private RedisCache redisCache;
    
    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private IPsyConsultColumnService psyConsultColumnService;

    @Resource
    private IPsyConsultServeConfigService psyConsultServeConfigService;

    /**
     * 查询心理咨询师列表
     */
    @PostMapping("/search")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyConsultReq req)
    {
        startPage();
        List<PsyConsult> list = psyConsultService.search(req);
        return getDataTable(list);
    }

    /**
     * 根据类型  查询咨询师列表
     */
    @ApiOperation(value = "查询咨询师列表")
    @PostMapping("/cache")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req)
    {
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.CONSULTANT_ID_LIST + "::" + listType);
        List<PsyConsult> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.CONSULTANT_BY_ID_KEY , PageUtils.paginate(idList));

        return getDataTable(cacheList, idList.size());
    }

    @PostMapping(value = "/getConsultWorksById/{id}")
    @RateLimiter
    public AjaxResult getConsultWorksById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getConsultWorksById(id));
    }


    /**
     * 获取心理咨询师详细信息
     */
    @PostMapping(value = "/{id}")
    @RateLimiter
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getOne(id));
    }

    @PostMapping(value = "/serve/{id}")
    @RateLimiter
    public AjaxResult getServe(@PathVariable("id") Long id)
    {
        PsyConsultServeConfigReq req = new PsyConsultServeConfigReq();
        req.setCId(id);
        req.setStatus("0");
        return AjaxResult.success(psyConsultServeConfigService.getList(req));
    }

    /**
     * 获取心理咨询师详细信息
     */
    @PostMapping(value = "/getConsultInfoByServe/{cId}/{sId}")
    @RateLimiter
    public AjaxResult getConsultInfoByServe(@PathVariable("cId") Long cId, @PathVariable("sId") Long sId)
    {
        return AjaxResult.success(psyConsultService.getConsultInfoByServe(cId, sId));
    }

    @PostMapping(value = "/getConsultColumn/{cat}/{id}")
    @RateLimiter
    public TableDataInfo getConsultColumn(@PathVariable("cat") String cat, @PathVariable("id") Long id)
    {
        startPage();
        List<PsyConsultColumnVO> list = new ArrayList<>();
        if (!"0".equals(cat) && !"1".equals(cat)) {
            return getDataTable(list);
        }
        PsyConsultColumnVO req = new PsyConsultColumnVO();
        req.setCat(cat);
        req.setStatus("0");
        if (id != 0L) {
            req.setConsultId(id);
        }

        list = psyConsultColumnService.getList(req);

        return getDataTable(list);
    }

    String agreement="<h2 class=\\\"ql-align-center\\\"><strong>心理咨询服务知情同意书</strong></h2><p class=\\\"ql-align-justify\\\">甲方（服务提供方）：壹加壹心理（运营主体：武汉仁心网络科技有限公司）</p><p class=\\\"ql-align-justify\\\">乙方（服务使用方）：</p><p class=\\\"ql-align-justify\\\">&nbsp;</p><p class=\\\"ql-align-justify\\\">尊敬的来访者：</p><p class=\\\"ql-align-justify\\\">感谢您预约壹加壹心理服务平台的心理咨询服务。</p><p class=\\\"ql-align-justify\\\">&nbsp;</p><p class=\\\"ql-align-justify\\\">根据《心理咨询师国家职业标准》、《精神卫生法》及国家相关法律法规，在相互尊重、相互信任、平等自愿、协商一致的前提下，甲乙双方就心理咨询具体事项达成如下协议：</p><p class=\\\"ql-align-justify\\\"><strong>1.协议适用</strong></p><p class=\\\"ql-align-justify\\\">1.1协议中乙方为完全行为能力人。限制行为能力人（主要针对未成年人）应委托监护人履行协议认同手续。本协议不针对无行为能力人。</p><p class=\\\"ql-align-justify\\\">1.2一经发现乙方不具备前述主体资格进行心理咨询的，由此产生的一切后果由乙方及其监护人承担，且甲方和为乙方提供咨询服务的甲方心理咨询师（以下统称：甲方咨询师）有权立即停止心理咨询。</p><p class=\\\"ql-align-justify\\\"><strong>2.责任、权利与义务</strong></p><p class=\\\"ql-align-justify\\\"><strong>2.1 乙方的权利和义务</strong></p><p class=\\\"ql-align-justify\\\">权利：</p><p class=\\\"ql-align-justify\\\">1.<strong>乙方</strong>可以根据个人意愿选择或更换心理咨询师；</p><p class=\\\"ql-align-justify\\\">2.<strong>乙方</strong>有权利了解所选择的心理咨询师的受训背景和执业资格；</p><p class=\\\"ql-align-justify\\\">3.对咨询方案、咨询收费、咨询时间，<strong>乙方</strong>有知情权、协商权和选择权。</p><p class=\\\"ql-align-justify\\\">义务 ：</p><p class=\\\"ql-align-justify\\\">1.<strong>乙方</strong>有义务向甲方咨询师提供与心理咨询有关的真实资料；</p><p class=\\\"ql-align-justify\\\">2.<strong>乙方</strong>有义务遵守甲方的相关规定；</p><p class=\\\"ql-align-justify\\\">3.<strong>乙方</strong>遵守和执行商定好的咨询方案、咨询收费、咨询时间等约定；</p><p class=\\\"ql-align-justify\\\">4.未成年人<strong>乙方</strong>的来访前来咨询和咨询结束离开时均应在监护人陪同之下，甲方咨询师不对未成年来访者的监管承担任何责任；</p><p class=\\\"ql-align-justify\\\">5.<strong>乙方</strong>应规避与心理咨询师发生除心理咨询以外的关系，<strong>乙方</strong>如果与甲方心理咨询师有心理咨询以外的关系，甲方对此不承担任何责任。</p><p class=\\\"ql-align-justify\\\">6.甲方心理咨询师应遵守心理咨询工作的伦理道德规则，不得与乙方建立除心理咨询以外的关系。甲方心理咨询师如果与<strong>乙方</strong>有心理咨询以外的关系，乙方有责任告知甲方，且甲方对此不承担任何责任。</p><p class=\\\"ql-align-justify\\\">7.乙方不得在心理咨询过程中利用言语或行为辱骂、性骚扰甲方心理咨询师，如有违反，甲方或甲方咨询师有权当即关闭心理咨询服务，并不予退款，且保留法律权利。</p><p class=\\\"ql-align-justify\\\"><strong>2.2 甲方的权利和义务</strong></p><p class=\\\"ql-align-justify\\\">权利：</p><p class=\\\"ql-align-justify\\\">1.<strong>甲方</strong>有权利选择合适的乙方，有权利了解与乙方心理问题有关的任何个人资料；</p><p class=\\\"ql-align-justify\\\">2.本着对乙方负责的态度，如乙方在评估后不适合做心理咨询，或者没有合适的心理咨询师与乙方一起工作，甲方有权利拒绝乙方预约。</p><p class=\\\"ql-align-justify\\\">义务：</p><p class=\\\"ql-align-justify\\\">1.甲方将安排适合的心理咨询师为乙方提供心理咨询服务；</p><p class=\\\"ql-align-justify\\\">2.甲方有义务向乙方介绍/展示所安排的心理咨询师的受训背景；</p><p class=\\\"ql-align-justify\\\"><strong>2.3 甲方心理咨询师的权利和义务</strong></p><p class=\\\"ql-align-justify\\\">权利：</p><p class=\\\"ql-align-justify\\\">1.心理咨询师在经过对乙方的初步评估后，有权利选择合适的乙方，并有权利了解与乙方心理问题有关的个人资料；</p><p class=\\\"ql-align-justify\\\">2.本着对乙方负责的态度，心理咨询师有权利提出转介或终止咨询。</p><p class=\\\"ql-align-justify\\\">义务：</p><p class=\\\"ql-align-justify\\\">1.遵守和执行商定好的咨询方案各方面的内容；</p><p class=\\\"ql-align-justify\\\">2.尊重乙方，遵守预约时间，如有特殊情况提前告知乙方并协同调整；</p><p class=\\\"ql-align-justify\\\">3.遵守职业道德及咨询伦理要求，遵守国家有关的法律法规；</p><p class=\\\"ql-align-justify\\\">4.严格遵守保密原则，并说明保密例外；</p><p class=\\\"ql-align-justify\\\">5.不得利用乙方获得咨询收费以外的其他利益；</p><p class=\\\"ql-align-justify\\\">6.心理咨询师不得因来访者的年龄、性别、种族、性取向、宗教和政治信仰、文化、身体状况、社会经济状况等任何方面的因素歧视乙方；</p><p class=\\\"ql-align-justify\\\">7.对于可能患有精神障碍的乙方，心理咨询师有义务建议乙方到合法医疗机构就诊。</p><p class=\\\"ql-align-justify\\\">8.心理咨询师不得与乙方发生除心理咨询以外的关系，不得与乙方私下达成交易。若发现此类情况，乙方有权终止和心理咨询师继续咨询的权利。</p><p class=\\\"ql-align-justify\\\"><strong>3.关于心理咨询时间和费用</strong></p><p class=\\\"ql-align-justify\\\"><strong>3.1收费：</strong></p><p class=\\\"ql-align-justify\\\">（1）乙方支付的费用是心理咨询师为乙方投入的时间和精力而付费，并不是为了保证咨询效果付费。心理咨询的最终效果，取决于乙方和心理咨询师共同努力的结果。心理咨询师对心理咨询效果不做担保和承诺；乙方理解并同意：达不到乙方心理预期的心理咨询效果，不属于服务质量问题，乙方不以此为由要求甲方退款，且甲方和甲方心理咨询师不承担任何法律上的责任。</p><p class=\\\"ql-align-justify\\\">（2）本平台提供的咨询服务一般按每次50分钟计费，具体的收费标准以咨询预约页面展示的价格为准。</p><p class=\\\"ql-align-justify\\\">（3）乙方须根据实际情况与心理咨询师协商咨询形式（面询、视频或语音）；甲方心理咨询师可与乙方协商具体的咨询次数，具体情况根据由双方协商决定。</p><p class=\\\"ql-align-justify\\\">（4）费用标准可能因为咨询师等级、优惠政策等原因进行不定期调整，乙方付费按实时的咨询预约页面为准。</p><p class=\\\"ql-align-justify\\\">（5）付费方式：先付费预约，后提供咨询服务。</p><p class=\\\"ql-align-justify\\\">（6）乙方如需更换心理咨询师，则需重新进行评估咨询和签订本咨询协议。</p><p class=\\\"ql-align-justify\\\"><strong>3.2时间</strong></p><p class=\\\"ql-align-justify\\\">（1）心理咨询会谈准点开始，如由于乙方原因迟到，咨询时间不顺延，咨询费用仍然按照原标准扣除，咨询时间按原限定时间结束；如果由于甲方心理咨询师原因迟到，咨询时间顺延补足或根据迟到时长退还相应的咨询费给乙方；</p><p class=\\\"ql-align-justify\\\">（2）如有请假情况，甲方心理咨询师和乙方均可在约定咨询时间的24小时之前，取消预约或更改本次预约时间，符合本条的取消和更改预约时间，无需支付违约费用。</p><p class=\\\"ql-align-justify\\\">（3）心理咨询违约，是指在约定咨询时间期间，因任何原因缺席或没有在约定咨询时间的24小时之前取消预约。如乙方违约，仍然需要按照原标准支付费用；如甲方心理咨询师违约，则需要为乙方提供一次免费的心理咨询作为补偿；</p><p class=\\\"ql-align-justify\\\">（4）心理咨询的长短及频率：心理咨询是定期会谈，为了确保心理咨询的连贯性，乙方需要为后续的会谈提前预约时间。甲方会尽力为乙方安排符合乙方需要的咨询时间，但具体安排以乙方和甲方心理咨询师具体商谈实际情况为准。至于间隔时间及频率，则要根据乙方的个别情况和具体需要而确定。</p><p class=\\\"ql-align-justify\\\"><strong>3.3 评估</strong></p><p class=\\\"ql-align-justify\\\">（1）心理咨询开始前，甲方心理咨询师有责任为乙方进行心理评估工作，具体次数由甲方心理咨询师根据乙方的实际情况为准，但必须反馈给乙方，并由乙方确认同意。</p><p class=\\\"ql-align-justify\\\">（2）评估不等同于心理咨询。旨在乙方了解甲方心理咨询师的工作风格，以确定是否在评估完成后，确定与甲方心理咨询师开始正式的心理咨询工作。甲方心理咨询师通过评估了解乙方是否适合心理咨询，以及确定自己是否能帮助到乙方。</p><p class=\\\"ql-align-justify\\\">（3）评估完成后，由双方共同决定是否正式开始心理咨询。甲方心理咨询师或乙方单方面决定开始心理咨询都不能视作正式心理咨询工作的开始。</p><p class=\\\"ql-align-justify\\\">（4）评估访谈涉及的咨询费用以当前页面展示的为准。</p><p class=\\\"ql-align-justify\\\"><strong>4.关于保密和资料使用</strong></p><p class=\\\"ql-align-justify\\\"><strong>4.1 保密性</strong></p><p class=\\\"ql-align-justify\\\">（1）甲方心理咨询师将严格遵守保密原则，未经乙方的同意，乙方的个人资料及会谈内容不会被告之任何第三方；</p><p class=\\\"ql-align-justify\\\">（2）甲方心理咨询师为了更好地服务于乙方，可能会提议对咨询过程进行录音、录像，在心理咨询之前由甲方心理咨询师和乙方沟通是否录制本次视屏。如乙方选择同意，则甲方心理咨询师可以进行录制，如乙方反对，甲方心理咨询师不可违背乙方意愿。</p><p class=\\\"ql-align-justify\\\"><strong>4.2保密例外：</strong></p><p class=\\\"ql-align-justify\\\">一般情况下，甲方心理咨询师对乙方所提供的信息保密，但如有以下情况，甲方心理咨询师不承诺履行保密义务，并会通知相关个人和机构：</p><p class=\\\"ql-align-justify\\\">1.触犯相关法律（包括并不限于甲方有可能对自己或他人构成严重危险）；</p><p class=\\\"ql-align-justify\\\">2.乙方涉及到自身的安全（包括并不限于自杀、自残、严重抑郁、严重的精神疾病等）不适合心理咨询工作；</p><p class=\\\"ql-align-justify\\\">3.法律规定的其他例外情况；</p><p class=\\\"ql-align-justify\\\">4.由于心理咨询工作的特殊性，咨询师在执业中需要被督导，咨询师有责任和权利选择任何正在进行的个案进行督导。督导具体相关信息属于咨询师的个人工作隐私，咨询师有权利保护个人工作隐私，以及不对来访者暴露相关信息。来访者开始进行咨询，即表示已经对此知情并且同意。咨询师不再另行告知；</p><p class=\\\"ql-align-justify\\\">5.如乙方患有精神分裂症，心境障碍等严重精神疾病，自知力不完整，需要精神科治疗时。</p><p class=\\\"ql-align-justify\\\"><strong>5.关于心理咨询中的转介、退出和终止</strong></p><p class=\\\"ql-align-justify\\\"><strong>5.1 自由退出（结束咨询关系）</strong></p><p class=\\\"ql-align-justify\\\">乙方有权提前2-3次向甲方心理咨询师提出结束咨询关系。同时，建议乙方在咨询中和心理咨询师讨论该想法，这也是心理咨询的意义所在，讨论会帮助乙方更好的理解中断咨询的想法与其心理状况之间的联系。同时也会是咨询中重要的转折。此外，若乙方在已预约好的时间内，并未提前通知中止当前咨询，甲方仍按照当次咨费进行收取。</p><p class=\\\"ql-align-justify\\\"><strong>5.2 心理咨询的转介</strong></p><p class=\\\"ql-align-justify\\\">甲方心理咨询师如果确认自己已不适合与乙方进行咨询工作时，将向乙方明确说明，并秉着对乙方负责的态度将乙方转介给其他适合的咨询师，由乙方确认新的心理咨询师后重新进行评估咨询和签订咨询协议。</p><p class=\\\"ql-align-justify\\\"><strong>5.3终止</strong></p><p class=\\\"ql-align-justify\\\">1.心理咨询师如果在咨询过程中发现乙方可能患有精神障碍，或有严重自杀或者他杀倾向，生命处于危险，有自杀、受虐或暴力倾向历史，出现幻觉以及有药物或酒精滥用等不适宜进行心理咨询的情况，甲方有权终止心理咨询并建议乙方到相关医疗机构就诊；</p><p class=\\\"ql-align-justify\\\">（2）如果乙方在心理咨询期间存在违反法律法规的行为、咨询过程内外的攻击性行为、可能对自己或他人构成危险的行为等不适宜继续进行心理咨询的行为，甲方有权终止心理咨询。</p><p class=\\\"ql-align-justify\\\"><strong>6.非面询服务</strong></p><p class=\\\"ql-align-justify\\\">（1）如甲方心理咨询师认为乙方不适宜非面询服务时，会建议乙方进行面询等其他咨询方式；</p><p class=\\\"ql-align-justify\\\">（2）为了确保安全的咨询环境，避免隐私泄漏，未经甲乙双方同意，任何一方请勿对非面询的咨询过程进行录音、录像等；</p><p class=\\\"ql-align-justify\\\">（3）为了保证咨询顺利进行，请乙方在远程咨询前做好相应准备，同时对于远程咨询中可能出现的网络、电力、信号中断等设备意外做好心理上的准备。如遇见因为乙方设备网络原因导致咨询无法继续或者迟到，参照3.2中（1）条执行。如遇见因为甲方心理咨询师设备网络原因导致咨询无法继续或者迟到，参照3.2中（1）条执行；</p><p class=\\\"ql-align-justify\\\">（4）请乙方认真阅读此协议，电子版协议签署具备法律效力。</p><p class=\\\"ql-align-justify\\\"><strong>7.违约责任：</strong></p><p class=\\\"ql-align-justify\\\">7.1乙方未完成付款和确认预约咨询信息的情况下，预约咨询订单将自动失效。</p><p class=\\\"ql-align-justify\\\">7.2乙方完成付款但因个人原因未在约定时间进入咨询，或因个人原因未按约定在咨询前提前24小时告知心理咨询师取消和调整咨询的情况下，咨询费用需要照常收取。</p><p class=\\\"ql-align-justify\\\">7.3甲方心理咨询师在乙方付款确认预约咨询后因个人原因未按时完成咨询，或因个人原因未按约定在咨询前提前24小时告知乙方取消和调整咨询的情况下，咨询费用退还。</p><p class=\\\"ql-align-justify\\\">7.4乙方不得与甲方平台上的心理咨询师进行私下交易 (包括但不限于私下转账交易或通过第三方平台交易)。</p><p class=\\\"ql-align-justify\\\">甲方平台发现乙方与甲方签约咨询师存在私下交易行为的，平台有权直接注销账户并拒绝继续提供咨询服务、且已收取的咨询费用不予退还；乙方与甲方签约心理咨询师因私下交易发生纠纷的由其双方自行解决，与平台无关，因此给平台造成的任何损失，乙方应当承担相应的赔偿责任。</p><p class=\\\"ql-align-justify\\\"><strong>8、退费规则</strong></p><p class=\\\"ql-align-justify\\\">8.1乙方购买咨询服务后一次未使用的情况下要求退款的可以全额退款：</p><p class=\\\"ql-align-justify\\\">8.2乙方按甲方咨询师正常单次收费标准一次性预缴纳多次咨询费未使用完的，经双方沟通一致，可以退还余下次数的费用。例如：咨询当前正常收费为300元/次， 乙方一性次缴纳了10次咨询费3000元，已完成 8次咨询（8*300=2400元），经咨访双方沟通一致，认为可以结束咨询的，对剩余未使用的2次咨询服务对应的费用（2*300=600元）可以退费;</p><p class=\\\"ql-align-justify\\\">8.3[优惠活动订单退费] 乙方通过平台的优惠活动预约的咨询订单未全部使用，乙方向平台申请退费的情况，则已使用的订单次数不享有活动优惠。如: 乙方通过平台的优惠活动预约了 10 次咨询，完成 8次咨询服务后，乙方申请结束咨询并要求退还余下费用，那么，退费计算规则为：已使用的 8次咨询服务按其预约咨询时咨询详情页面展示的咨询师原价核算，用乙方实际支付的优惠金额扣除已使用的8次咨询服务的原价金额后，平台将剩余的费用退还给乙方 (来访者实际支付的金额不足以扣除已使用 8次咨询服务原价金额的，来访者无需另行补足费用，平台不予退费) ;</p><p class=\\\"ql-align-justify\\\">8.4咨询服务全部完成的，不予退费;</p><p class=\\\"ql-align-justify\\\">8.5退费申请通过后，对应的费用在7个工作日内原路退回:因结算周期等因素导致延迟退费的，以实际退款时间为准。</p><p class=\\\"ql-align-justify\\\">8.6甲方对乙方与咨询师的费用纠纷提供亚等协商干预，具体方案以乙方与咨询师协商一致为准，甲方不以任何形式承担补偿赔偿责任，责任由过错方承担。</p><p class=\\\"ql-align-justify\\\">&nbsp;</p><p class=\\\"ql-align-justify\\\"><strong>9.免责条款：</strong></p><p class=\\\"ql-align-justify\\\">9.1如乙方故意隐瞒与咨询有关的真实情况从而造成乙方心理问题加重以及由此带来的不良后果，甲方心理咨询师不承担责任。</p><p class=\\\"ql-align-justify\\\">9.2在咨询协议期内以及咨询关系结束之后，对于乙方在咨询之外发生的意外人身伤害或者财产损失，甲方心理咨询师不承担责任。</p><p class=\\\"ql-align-justify\\\">9.3心理危机高风险行为</p><p class=\\\"ql-align-justify\\\">（1）如乙方在这之前有过自杀或伤害他人倾向与行为，甲方心理咨询师会尽所能帮助乙方，但不能保证心理咨询与治疗期间乙方的冲动会马上减缓，也不能保证乙方今后不再有自杀或伤害他人行为与倾向。请乙方在有自杀冲动或伤害他人行为之前致电甲方心理咨询师，共同探讨更有建设性的问题解决之道。但如果乙方最终选择自杀或伤害他人，作为一个完全民事行为能力人，乙方承担全部责任与行为后果。甲方心理咨询师不承担任何连带民事或刑事责任；</p><p class=\\\"ql-align-justify\\\">（2）在心理咨询过程中，乙方因自身原因而突发疾病或意外死亡，或因自身原因造成情绪失控而发生自伤、自残、自杀等后果，甲方心理咨询师应及时采取措施控制事态恶化，必要时可与乙方的监护人、家属及相关部门取得联系，以保护求助者人身安全。但因此造成的损害后果，甲方心理咨询师不承担责任。</p><p class=\\\"ql-align-justify\\\">9.4乙方给第三人带来了损失，甲方心理咨询师不承担责任。</p><p class=\\\"ql-align-justify\\\">9.5因不可抗力不能履行合同的，根据不可抗力的影响，部分或者全部免除责任。</p><p class=\\\"ql-align-justify\\\"><strong>10.争议解决方式：</strong></p><p class=\\\"ql-align-justify\\\">甲乙双方在履行本协议中发生的争议，由双方协商解决。协商不成的，依法向甲方所在地人民法院诉讼解决。</p><p class=\\\"ql-align-justify\\\"><strong>11.其他事宜：</strong></p><p class=\\\"ql-align-justify\\\">11.1关于上门提供心理咨询：甲方心理咨询师谢绝一切上门提供心理咨询的邀请。</p><p class=\\\"ql-align-justify\\\">11.2紧急联络：</p><p class=\\\"ql-align-justify\\\">乙方需要提供一位联系人，并保证信息的有效性。在紧急情况下（包括并不限于在乙方存在危及自身生命的情况等），心理咨询师将与该联系人取得联系，通报乙方的状况，但不会将乙方的隐私内容透露给该联系人，如果乙方提供的联系人信息无效，由此导致的一切后果均由乙方本人承担。</p><p class=\\\"ql-align-justify\\\"><strong>12.法律效力声明：</strong></p><p class=\\\"ql-align-justify\\\">12.1本协议电子版本由双方填写确认，具有同等法律效力。</p><p class=\\\"ql-align-justify\\\">12.2如发生以下情况，本协议自动终止：</p><p class=\\\"ql-align-justify\\\">乙方需要转介、退出咨询以及咨询结束时。协议未尽事宜，经协商后另行签署补充协议；</p><p class=\\\"ql-align-justify\\\">12.3本协议乙方无论用真实姓名签订或者用化名签订，均为乙方真实意思表示，具有同等的法律效力。</p><p class=\\\"ql-align-justify\\\"><strong>13.签字同意：</strong></p><p class=\\\"ql-align-justify\\\">13.1乙方已经阅读并理解上述条款。乙方同意与甲方心理咨询师建立职业性咨访关系；</p><p class=\\\"ql-align-justify\\\">13.2乙方提供的紧急情况联系人信息由乙方对所提供的信息承担一切法律责任；</p><p class=\\\"ql-align-justify\\\"><strong>14. 特别提示</strong></p><p class=\\\"ql-align-justify\\\">14.1 甲方心理咨询师为乙方提供的心理咨询服务不涉及心理治疗以及精神障碍诊断治疗；</p><p class=\\\"ql-align-justify\\\">14.2 甲方与乙方建立的是心理咨询服务合同关系。</p><p class=\\\"ql-align-justify\\\">以上条款最终解释权归甲方所有</p><p class=\\\"ql-align-justify\\\">【重要提示】乙方一旦签名表明：</p><p class=\\\"ql-align-justify\\\">1、乙方已阅读并理解了以上全部内容；</p><p class=\\\"ql-align-justify\\\">2、甲方心理咨询师已对乙方履行告知义务并对乙方的疑问给予了令乙方满意的回答。</p><p class=\\\"ql-align-justify\\\">3、乙方在本协议中签属的微信昵称或在与甲方咨询师实际咨询中所使用的名称具有与乙方真实姓名同等法律效力。</p><p class=\\\"ql-align-justify\\\">&nbsp;</p>\",\n" ;

    /**
     * 咨询协议
     * @return
     */
    @PostMapping(value = "/agreement")
    @RateLimiter
    public AjaxResult getAgreement()
    {

        return AjaxResult.success("succ",agreement);
    }

}
