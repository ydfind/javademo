package ch.qos.logback.demo.prime;

import ch.qos.logback.demo.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static ch.qos.logback.classic.ClassicConstants.FINALIZE_SESSION_MARKER;

public class PrimeAction extends Action {

  Logger logger = LoggerFactory.getLogger(PrimeAction.class);
  static Marker SMTP_TRIGGER = MarkerFactory.getMarker("SMTP_TRIGGER");
  static {
     // markers can hold references to other markers
     SMTP_TRIGGER.add(FINALIZE_SESSION_MARKER);
  }

  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

    PrimeForm form = (PrimeForm) actionForm;


    Long number = form.getNumber();
    MDC.put("txId", String.valueOf(number));

    if (number == 99) {
      logger.info("99 is a magical value", new Exception("99 is supposedly invalid"));
    }

    try {
      NumberCruncher nc = new NumberCruncherImpl();
      Long start = System.currentTimeMillis();
      Long[] result = nc.factor(number);
      Long duration = System.currentTimeMillis() - start;
      logger.info("Results computed in {} ms", duration);

      request.setAttribute(Constants.PRIME_NUMBER, number);
      request.setAttribute(Constants.PRIME_DURATION, duration);
      request.setAttribute(Constants.PRIME_RESULTS, result);
      return actionMapping.findForward("next");
    } finally {
      logger.info(SMTP_TRIGGER, "Prime computation ended");
      MDC.put("txId", null);
    }
  }
}