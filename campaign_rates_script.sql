Create Table Campaign_Rates(
    audience_size numrange,
    email float8,
    sms float8,
    direct_mail float8
);

INSERT INTO public.campaign_rates(
    audience_size, email, sms, direct_mail)
    VALUES (numrange(0,5000,'[]'), 0, 0, 0),
     (numrange(5001, 15000, '[]'), 0.66, 1.10, 1.56),
     (numrange(15001, 25000, '[]'), 0.42, 0.57, 1.00),
     (numrange(25001, 50000, '[]'), 0.27, 0.41, 0.70),
     (numrange(50001, null, '[]'), 0.17, 0.25, 0.62);