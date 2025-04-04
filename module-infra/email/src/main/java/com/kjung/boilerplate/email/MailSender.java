package com.kjung.boilerplate.email;

public interface MailSender {
    /**
     * 이메일을 전송합니다.
     *
     * @param subject   이메일 제목입니다. UTF-8로 인코딩되며 Base64 방식으로 인코딩될 수 있습니다.
     * @param content   이메일 본문 내용입니다. {@code isHtml}이 {@code true}인 경우 HTML로 처리됩니다.
     * @param toAddress 수신자 이메일 주소입니다. 단일 주소만 지원합니다.
     * @param cc        참조(CC) 받을 이메일 주소 배열입니다. {@code null} 또는 빈 배열일 수 있습니다.
     * @param bcc       숨은 참조(BCC) 받을 이메일 주소 배열입니다. {@code null} 또는 빈 배열일 수 있습니다.
     * @param isHtml    본문이 HTML 형식인지 여부를 나타냅니다. {@code true}이면 HTML로, {@code false}이면 일반 텍스트로 전송됩니다.
     */
    void sendMail(String subject,
                  String content,
                  String toAddress,
                  String[] cc,
                  String[] bcc,
                  boolean isHtml);
}
