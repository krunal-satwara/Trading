package com.trade.withdrawalservice;

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeWithdrawalServiceApplicationTests {

    @Autowired
    private WithdrawalServiceImpl withdrawalServiceImpl;

    @MockBean
    private WithdrawalRepository withdrawalRepository;

    private static final Withdrawal withdrawal = new Withdrawal(1L, 2L, "17-7-2020", "01:41:55", 10000, "accName", 675, "bankName", "686652871", 1, "remarks");

    @Test
    public void createWithdrawal() {
        when(withdrawalRepository.save(withdrawal)).thenReturn(withdrawal);
        assertEquals(withdrawal, withdrawalServiceImpl.withdrawal(withdrawal));
    }

    @Test
    public void getWithdrawal() {
        Long withdrawalId = withdrawal.getWithdrawalId();
        when(withdrawalRepository.findById(withdrawalId)).thenReturn(java.util.Optional.of(withdrawal));
        assertEquals(withdrawal, withdrawalServiceImpl.withdrawal(withdrawalId).get());
    }

    @Test
    public void getWithdrawalList() {
        when(withdrawalRepository.findAll()).thenReturn(Stream.of(new Withdrawal(1L, 2L, "17-7-2020", "01:41:55", 10000, "accName", 675, "bankName", "686652871", 1, "remarks")).collect(Collectors.toList()));
        assertEquals(1, withdrawalServiceImpl.withdrawals().size());
    }
}*/
