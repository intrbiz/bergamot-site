package com.intrbiz.discuss.mailer;

public class DiscussMailer
{
    private static final DiscussMailer US = new DiscussMailer();
    
    public static final DiscussMailer get()
    {
        return US;
    }
    
    private DiscussMailFetcher fetcher;
    
    private DiscussMailSender sender;
    
    private DiscussMailer()
    {
        super();
    }
    
    public void configure(DiscussMailFetcher fetcher, DiscussMailSender sender)
    {
        this.fetcher = fetcher;
        this.sender = sender;
    }
    
    public void start()
    {
        if (this.fetcher != null) this.fetcher.start();
        if (this.sender != null) this.sender.start();
    }

    public DiscussMailFetcher getFetcher()
    {
        return fetcher;
    }

    public DiscussMailSender getSender()
    {
        return sender;
    }
}
