unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, StdCtrls, ExtCtrls, ComCtrls;

const
  addrSP = $F6; {- адрес указателя стека. Стек имеет адреса ниже данного}

type
  TForm1 = class(TForm)
    ExitButton: TSpeedButton;
    GroupBox1: TGroupBox;
    Digit_0: TSpeedButton;      {Кнопки клавиатуры данных}
    Digit_1: TSpeedButton;
    Digit_2: TSpeedButton;
    Digit_3: TSpeedButton;
    Digit_4: TSpeedButton;
    Digit_5: TSpeedButton;
    Digit_6: TSpeedButton;
    Digit_7: TSpeedButton;
    Digit_8: TSpeedButton;
    Digit_9: TSpeedButton;
    Digit_A: TSpeedButton;
    Digit_B: TSpeedButton;
    Digit_C: TSpeedButton;
    Digit_D: TSpeedButton;
    Digit_E: TSpeedButton;
    Digit_F: TSpeedButton;
    KeyReset: TSpeedButton;
    KeyRunStep: TSpeedButton;
    KeyRunCycle: TSpeedButton;
    KeyLoadAcc: TSpeedButton;
    KeySetAddr: TSpeedButton;
    KeyReadInc: TSpeedButton;
    KeyReadDec: TSpeedButton;
    KeyWriteInc: TSpeedButton;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    GroupBox4: TGroupBox;
    GroupBox5: TGroupBox;
    GroupBox6: TGroupBox;
    GroupBox7: TGroupBox;
    GroupBox8: TGroupBox;
    GroupBox10: TGroupBox;
    TRG0: TCheckBox;  {Значение 0-го бита в TRG }
    TRG1: TCheckBox;  {   -"-   1-го     -"-    }
    TRG2: TCheckBox;  {   -"-   2-го     -"-    }
    TRG3: TCheckBox;  {   -"-   3-го     -"-    }
    TRG4: TCheckBox;  {   -"-   4-го     -"-    }
    TRG5: TCheckBox;  {   -"-   5-го     -"-    }
    TRG6: TCheckBox;  {   -"-   6-го     -"-    }
    TRG7: TCheckBox;
    DD7: TShape;  {объекты для работы}
    DD6: TShape;  {с индикатором DD}
    DD5: TShape;
    DD4: TShape;
    DD3: TShape;
    DD2: TShape;
    DD1: TShape;
    DD0: TShape;
    DA7: TShape;  {объекты для работы}
    DA6: TShape;  {с индикатором DA}
    DA5: TShape;
    DA4: TShape;
    DA3: TShape;
    DA2: TShape;
    DA1: TShape;
    DA0: TShape;
    DM7: TShape;  {объекты для работы}
    DM6: TShape;  {с индикатором DM}
    DM5: TShape;
    DM4: TShape;
    DM3: TShape;
    DM2: TShape;
    DM1: TShape;
    DM0: TShape;
    DRG7: TShape; {объекты для работы}
    DRG6: TShape; {с индикатором DRG}
    DRG5: TShape;
    DRG4: TShape;
    DRG3: TShape;
    DRG2: TShape;
    DRG1: TShape;
    DRG0: TShape;
    drgDD1: TShape;
    drgDD2: TShape;
    drgDD3: TShape;
    drgDD4: TShape;
    drgDD5: TShape;
    drgDD6: TShape;
    drgDD7: TShape;
    drgDD8: TShape;
    drgDA1: TShape;
    drgDA2: TShape;
    drgDA3: TShape;
    drgDA4: TShape;
    drgDA5: TShape;
    drgDA6: TShape;
    drgDA7: TShape;
    drgDA8: TShape;
    drgDL1: TShape;
    drgDL2: TShape;
    drgDL3: TShape;
    drgDL4: TShape;
    drgDL5: TShape;
    drgDL6: TShape;
    drgDL7: TShape;
    drgDL8: TShape;
    drgDD9: TShape;
    drgDD10: TShape;
    drgDD11: TShape;
    drgDD12: TShape;
    drgDD13: TShape;
    drgDD14: TShape;
    drgDD15: TShape;
    drgDD16: TShape;
    drgDA9: TShape;
    drgDA10: TShape;
    drgDA11: TShape;
    drgDA12: TShape;
    drgDA13: TShape;
    drgDA14: TShape;
    drgDA15: TShape;
    drgDA16: TShape;
    drgDL9: TShape;
    drgDL10: TShape;
    drgDL11: TShape;
    drgDL12: TShape;
    drgDL13: TShape;
    drgDL14: TShape;
    drgDL15: TShape;
    drgDL16: TShape;
    drgDL17: TShape;
    drgDL18: TShape;
    drgDL19: TShape;
    drgDL20: TShape;
    drgDL21: TShape;
    drgDL22: TShape;
    drgDL23: TShape;
    drgDL24: TShape;
    drgDL25: TShape;
    drgDL26: TShape;
    drgDL27: TShape;
    drgDL28: TShape;
    drgDL29: TShape;
    drgDL30: TShape;
    drgDL31: TShape;
    drgDL32: TShape;
    FullReset: TSpeedButton;
    AboutButton: TSpeedButton;
    TR0: TShape;
    TR1: TShape;
    TR2: TShape;
    TR3: TShape;
    TR4: TShape;
    TR5: TShape;
    TR6: TShape;
    TR7: TShape;
    RegsPanel: TBevel;
    Bevel2: TBevel;
    SoundPanel: TGroupBox;
    Bevel1: TBevel;
    Bevel3: TBevel;
    Label4: TLabel;
    Label5: TLabel;
    NoteC: TShape;
    NoteCd: TShape;
    NoteD: TShape;
    NoteDd: TShape;
    NoteE: TShape;
    NoteF: TShape;
    NoteFd: TShape;
    NoteG: TShape;
    NoteGd: TShape;
    NoteA: TShape;
    NoteAd: TShape;
    NoteB: TShape;
    Pause: TShape;
    NoteC01: TShape;
    NoteC02: TShape;
    NoteC03: TShape;
    NoteC04: TShape;
    NoteC05: TShape;
    NoteC06: TShape;
    NoteC07: TShape;
    NoteC08: TShape;
    NoteC09: TShape;
    NoteC10: TShape;
    NoteCd01: TShape;
    NoteCd02: TShape;
    NoteCd03: TShape;
    NoteCd04: TShape;
    NoteCd05: TShape;
    NoteCd06: TShape;
    NoteCd07: TShape;
    NoteCd08: TShape;
    NoteCd09: TShape;
    NoteCd10: TShape;
    NoteD01: TShape;
    NoteD02: TShape;
    NoteD03: TShape;
    NoteD04: TShape;
    NoteD05: TShape;
    NoteD06: TShape;
    NoteD07: TShape;
    NoteD08: TShape;
    NoteD09: TShape;
    NoteD10: TShape;
    NoteDd01: TShape;
    NoteDd02: TShape;
    NoteDd03: TShape;
    NoteDd04: TShape;
    NoteDd05: TShape;
    NoteDd06: TShape;
    NoteDd07: TShape;
    NoteDd08: TShape;
    NoteDd09: TShape;
    NoteDd10: TShape;
    NoteE01: TShape;
    NoteE02: TShape;
    NoteE03: TShape;
    NoteE04: TShape;
    NoteE05: TShape;
    NoteE06: TShape;
    NoteE07: TShape;
    NoteE08: TShape;
    NoteE09: TShape;
    NoteE10: TShape;
    NoteF01: TShape;
    NoteF02: TShape;
    NoteF03: TShape;
    NoteF04: TShape;
    NoteF05: TShape;
    NoteF06: TShape;
    NoteF07: TShape;
    NoteF08: TShape;
    NoteF09: TShape;
    NoteF10: TShape;
    NoteFd01: TShape;
    NoteFd02: TShape;
    NoteFd03: TShape;
    NoteFd04: TShape;
    NoteFd05: TShape;
    NoteFd06: TShape;
    NoteFd07: TShape;
    NoteFd08: TShape;
    Notefd09: TShape;
    NoteFd10: TShape;
    NoteG01: TShape;
    NoteG02: TShape;
    NoteG03: TShape;
    NoteG04: TShape;
    NoteG05: TShape;
    NoteG06: TShape;
    NoteG07: TShape;
    NoteG08: TShape;
    NoteG09: TShape;
    NoteG10: TShape;
    NoteGd01: TShape;
    NoteGd02: TShape;
    NoteGd03: TShape;
    NoteGd04: TShape;
    NoteGd05: TShape;
    NoteGd06: TShape;
    NoteGd07: TShape;
    NoteGd08: TShape;
    NoteGd09: TShape;
    NoteGd10: TShape;
    NoteA01: TShape;
    NoteA02: TShape;
    NoteA03: TShape;
    NoteA04: TShape;
    NoteA05: TShape;
    NoteA06: TShape;
    NoteA07: TShape;
    NoteA08: TShape;
    NoteA09: TShape;
    NoteA10: TShape;
    NoteAd01: TShape;
    NoteAd02: TShape;
    NoteAd03: TShape;
    NoteAd04: TShape;
    NoteAd05: TShape;
    NoteAd06: TShape;
    NoteAd07: TShape;
    NoteAd08: TShape;
    NoteAd09: TShape;
    NoteAd10: TShape;
    NoteB01: TShape;
    NoteB02: TShape;
    NoteB03: TShape;
    NoteB04: TShape;
    NoteB05: TShape;
    NoteB06: TShape;
    NoteB07: TShape;
    NoteB08: TShape;
    NoteB09: TShape;
    NoteB10: TShape;
    Pause01: TShape;
    Pause02: TShape;
    Pause03: TShape;
    Pause04: TShape;
    Pause05: TShape;
    Pause06: TShape;
    Pause07: TShape;
    Pause08: TShape;
    Pause09: TShape;
    Pause10: TShape;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label1: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    Label9: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    Label10: TLabel;
    Label31: TLabel;
    Label32: TLabel;
    Label33: TLabel;
    Label34: TLabel;
    Label35: TLabel;
    Label36: TLabel;
    Label37: TLabel;
    Label11: TLabel;
    Label38: TLabel;
    Label39: TLabel;
    Label40: TLabel;
    Label41: TLabel;
    Label42: TLabel;
    Label43: TLabel;
    Label44: TLabel;
    Label2: TLabel;
    Label45: TLabel;
    Label46: TLabel;
    Label47: TLabel;
    Label48: TLabel;
    Label49: TLabel;
    Label50: TLabel;
    Label51: TLabel;
    Label52: TLabel;
    Label53: TLabel;
    Label54: TLabel;
    Label55: TLabel;
    Label56: TLabel;
    Label3: TLabel;
    Label57: TLabel;
    Label58: TLabel;
    Label59: TLabel;
    Label60: TLabel;
    Label61: TLabel;
    Label62: TLabel;
    Label63: TLabel;
    Label64: TLabel;
    Label65: TLabel;
    Label66: TLabel;
    Label67: TLabel;
    Label68: TLabel;
    Reg0: TLabel;
    Reg1: TLabel;
    Reg2: TLabel;
    Reg3: TLabel;
    Reg4: TLabel;
    Reg5: TLabel;
    Reg6: TLabel;
    Reg7: TLabel;
    Accum: TLabel;
    OctC: TLabel;
    OctCd: TLabel;
    OctD: TLabel;
    OctDd: TLabel;
    OctE: TLabel;
    OctF: TLabel;
    OctFd: TLabel;
    OctG: TLabel;
    OctGd: TLabel;
    OctA: TLabel;
    OctAd: TLabel;
    OctB: TLabel;
    procedure ExitButtonClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    {Процедура переключения индикатора с номером IndicatorID в состояние,
     определённое в Indicator}
    procedure SetAndTurnIndicator(const IndID: Byte; const Indicator: Byte);
    procedure KeyResetClick(Sender: TObject);
    procedure KeyRunCycleClick(Sender: TObject);
    procedure KeySetAddrClick(Sender: TObject);
    procedure Digit_0Click(Sender: TObject);
    procedure Digit_1Click(Sender: TObject);
    procedure Digit_2Click(Sender: TObject);
    procedure Digit_3Click(Sender: TObject);
    procedure Digit_4Click(Sender: TObject);
    procedure Digit_5Click(Sender: TObject);
    procedure Digit_6Click(Sender: TObject);
    procedure Digit_7Click(Sender: TObject);
    procedure Digit_8Click(Sender: TObject);
    procedure Digit_9Click(Sender: TObject);
    procedure Digit_AClick(Sender: TObject);
    procedure Digit_BClick(Sender: TObject);
    procedure Digit_CClick(Sender: TObject);
    procedure Digit_DClick(Sender: TObject);
    procedure Digit_EClick(Sender: TObject);
    procedure Digit_FClick(Sender: TObject);
    procedure KeyWriteIncClick(Sender: TObject);
    procedure KeyReadIncClick(Sender: TObject);
    procedure KeyReadDecClick(Sender: TObject);
    procedure KeyRunStepClick(Sender: TObject);
    procedure TRG0Click(Sender: TObject);
    procedure TRG1Click(Sender: TObject);
    procedure TRG2Click(Sender: TObject);
    procedure TRG3Click(Sender: TObject);
    procedure TRG4Click(Sender: TObject);
    procedure TRG5Click(Sender: TObject);
    procedure TRG6Click(Sender: TObject);
    procedure TRG7Click(Sender: TObject);
    procedure FullResetClick(Sender: TObject);
    procedure AboutButtonClick(Sender: TObject);
    procedure KeyLoadAccClick(Sender: TObject);
  private
    { Private declarations }
    DD, DA, DM,  {DA - текущий адрес}
    DRG: Byte; { - содержимое соответствующих индикаторов}
    MPMemory: array [$0..$FF] of Byte; {ОЗУ микропроцессора}
    Waiting4Num: Byte; { - устанавливается управляющими командами,
    если после их нажатия необходимо произвести ввод данных с клавиатуры
    данных. Присваивается "2", и после ввода каждой (1-й, 2-й) уменьшается
    на единицу. Если вместо ввода числа выбрать команду, то она, команда,
    будет проигнорирована}
    ReadingNewAddr,
    ReadingNewAcc: Boolean;
    InputBuffer: String[3];{Буфер ввода чисел
          в 16-й с.с. Используется InputDigit}
    procedure InputDigit(const Digit: Char);
    {Выполняет программу с текущего адреса. Если RunMode = True,
     то выполняется вся программа, иначе только одна команда}
    procedure RunProgramm(const RunMode: Boolean);
    {Выполняет вывод на 7-сегментное табло}
    procedure TurnDRG(const drgNum: Byte; const NewValue: Byte);
    {Выводит 16-ю цифру на DRG с номером drgNum}
    procedure OutDigitToDRG(const drgNum: Byte; const Digit: Byte);
    function GetTRG: Byte;
    property TRG: Byte read GetTRG;
    function GetRegister(Index: Byte): Byte;
    procedure SetRegister(Index: Byte; NewValue: Byte);
    procedure SetAccumulator(const NewValue: Byte);
    procedure Out2SoundCard(const Note, Duration: Byte);
    {Регистры от R[0]..R[7]}
    property R[Index: Byte]: Byte read GetRegister write SetRegister;
    {Аккумулятор}
    property AC: Byte read MPMemory[$FF] write SetAccumulator;
    {Указатель стека}
    property SP: Byte read MPMemory[addrSP] write MPMemory[addrSP];
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.DFM}

const
  {Идентификаторы индикаторов}
  indDD  = 0;  indDA  = 1;
  indDM  = 2;  indDRG = 3;
  indTRG = 4;

{ Выводит звуковую информацию на плату звуковой индикации }
procedure TForm1.Out2SoundCard(const Note, Duration: Byte);
    procedure PlayNote(const NumNote, Octave: Byte);
        procedure ShowNote(D1, D2, D3, D4, D5, D6, D7, D8, D9, D10: TShape; const Color: LongInt);
        var
                  i: Byte;
                tmp: TShape;
           prevTime: TDateTime;
           Hour,Min,
           Sec,MSec: Word;
        begin
           for i := 1 to Duration div 25 do
            begin
               case i of
                 1: tmp := D1;     2: tmp := D2;
                 3: tmp := D3;     4: tmp := D4;
                 5: tmp := D5;     6: tmp := D6;
                 7: tmp := D7;     8: tmp := D8;
                 9: tmp := D9;    10: tmp := D10
               end;
               with tmp do
                begin
                   Brush.Color := Color;
                   Pen.Color   := clBlack;
                   Refresh
                end;
               prevTime := Time;
               while True do
                begin
                   DecodeTime(Time-prevTime, Hour, Min, Sec, MSec);
                   if Sec*1000+MSec >= 100 then
                     Break
                end
            end;
           for i := 1 to Duration div 25 do
            begin
               case i of
                 1: tmp := D1;     2: tmp := D2;
                 3: tmp := D3;     4: tmp := D4;
                 5: tmp := D5;     6: tmp := D6;
                 7: tmp := D7;     8: tmp := D8;
                 9: tmp := D9;    10: tmp := D10
               end;
               with tmp do
                begin
                   Brush.Color := clSilver;
                   Pen.Color   := clGray
                end
            end;
        end;
    var
       tmpStr: String[1];
    begin
       tmpStr[0] := Char(1);
       case NumNote of
  {C }   0: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctC.Caption := tmpStr;
               ShowNote(NoteC01,NoteC02,NoteC03,NoteC04,NoteC05,NoteC06,NoteC07,NoteC08,NoteC09,NoteC10, clMaroon);
               OctC.Caption := '1'
            end;
  {C#}   1: begin
               tmpStr[1]     := Char(Byte('0') + Octave);
               OctCd.Caption := tmpStr;
               ShowNote(NoteCd01,NoteCd02,NoteCd03,NoteCd04,NoteCd05,NoteCd06,NoteCd07,NoteCd08,NoteCd09,NoteCd10, clMaroon);
               OctCd.Caption := '1'
            end;
  {D }   2: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctD.Caption := tmpStr;
               ShowNote(NoteD01,NoteD02,NoteD03,NoteD04,NoteD05,NoteD06,NoteD07,NoteD08,NoteD09,NoteD10, clOlive);
               OctD.Caption := '1'
            end;
  {D#}   3: begin
               tmpStr[1]     := Char(Byte('0') + Octave);
               OctDd.Caption := tmpStr;
               ShowNote(NoteDd01,NoteDd02,NoteDd03,NoteDd04,NoteDd05,NoteDd06,NoteDd07,NoteDd08,NoteDd09,NoteDd10, clOlive);
               OctDd.Caption := '1'
            end;
  {E }   4: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctE.Caption := tmpStr;
               ShowNote(NoteE01,NoteE02,NoteE03,NoteE04,NoteE05,NoteE06,NoteE07,NoteE08,NoteE09,NoteE10, $00800040);
               OctE.Caption := '1'
            end;
  {F }   5: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctF.Caption := tmpStr;
               ShowNote(NoteF01,NoteF02,NoteF03,NoteF04,NoteF05,NoteF06,NoteF07,NoteF08,NoteF09,NoteF10, clBlue);
               OctF.Caption := '1'
            end;
  {F#}   6: begin
               tmpStr[1]     := Char(Byte('0') + Octave);
               OctFd.Caption := tmpStr;
               ShowNote(NoteFd01,NoteFd02,NoteFd03,NoteFd04,NoteFd05,NoteFd06,NoteFd07,NoteFd08,NoteFd09,NoteFd10, $00FF0080);
               OctFd.Caption := '1'
            end;
  {G }   7: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctG.Caption := tmpStr;
               ShowNote(NoteG01,NoteG02,NoteG03,NoteG04,NoteG05,NoteG06,NoteG07,NoteG08,NoteG09,NoteG10, $00408000);
               OctG.Caption := '1'
            end;
  {G#}   8: begin
               tmpStr[1]     := Char(Byte('0') + Octave);
               OctGd.Caption := tmpStr;
               ShowNote(NoteGd01,NoteGd02,NoteGd03,NoteGd04,NoteGd05,NoteGd06,NoteGd07,NoteGd08,NoteGd09,NoteGd10, $00808040);
               OctGd.Caption := '1'
            end;
  {A }   9: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctA.Caption := tmpStr;
               ShowNote(NoteA01,NoteA02,NoteA03,NoteA04,NoteA05,NoteA06,NoteA07,NoteA08,NoteA09,NoteA10, clLime);
               OctA.Caption := '1'
            end;
  {A#}  10: begin
               tmpStr[1]     := Char(Byte('0') + Octave);
               OctAd.Caption := tmpStr;
               ShowNote(NoteAd01,NoteAd02,NoteAd03,NoteAd04,NoteAd05,NoteAd06,NoteAd07,NoteAd08,NoteAd09,NoteAd10, clLime);
               OctAd.Caption := '1'
            end;
  {B }  11: begin
               tmpStr[1]    := Char(Byte('0') + Octave);
               OctB.Caption := tmpStr;
               ShowNote(NoteB01,NoteB02,NoteB03,NoteB04,NoteB05,NoteB06,NoteB07,NoteB08,NoteB09,NoteB10, clYellow);
               OctB.Caption := '1'
            end;
 {Pause}12: ShowNote(Pause01,Pause02,Pause03,Pause04,Pause05,Pause06,Pause07,Pause08,Pause09,Pause10, clGray)
       end
    end;
begin
   case Note of
    $0D: PlayNote( 0,1); $86: PlayNote( 0,2); $C3: PlayNote( 0,3); $E1: PlayNote( 0,4);
    $1B: PlayNote( 1,1); $8D: PlayNote( 1,2); $C6: PlayNote( 1,3); $E2: PlayNote( 1,4);
    $2B: PlayNote( 2,1); $93: PlayNote( 2,2); $C9: PlayNote( 2,3); $E4: PlayNote( 2,4);
    $34: PlayNote( 3,1); $99: PlayNote( 3,2); $CC: PlayNote( 3,3); $E6: PlayNote( 3,4);
    $3F: PlayNote( 4,1); $9F: PlayNote( 4,2); $CF: PlayNote( 4,3); $E7: PlayNote( 4,1);
    $4A: PlayNote( 5,1); $A4: PlayNote( 5,2); $D3: PlayNote( 5,3); $E8: PlayNote( 5,4);
    $54: PlayNote( 6,1); $AA: PlayNote( 6,2); $D4: PlayNote( 6,3); $EA: PlayNote( 6,4);
    $5E: PlayNote( 7,1); $AE: PlayNote( 7,2); $D7: PlayNote( 7,3); $EB: PlayNote( 7,4);
    $67: PlayNote( 8,1); $B3: PlayNote( 8,2); $D9: PlayNote( 8,3); $EC: PlayNote( 8,4);
    $6F: PlayNote( 9,1); $B7: PlayNote( 9,2); $DB: PlayNote( 9,3); $ED: PlayNote( 9,4);
    $77: PlayNote(10,1); $BB: PlayNote(10,2); $DD: PlayNote(10,3); $EE: PlayNote(10,4);
    $7F: PlayNote(11,1); $BF: PlayNote(11,2); $DF: PlayNote(11,3); $EF: PlayNote(11,4);
    $FF: PlayNote(12,0)
   end
end;

procedure TForm1.SetAndTurnIndicator(const IndID: Byte; const Indicator: Byte);
var
   tmp: Byte;
   procedure UpDate8Indicators(Ind0,Ind1,Ind2,Ind3,Ind4,Ind5,Ind6,Ind7: TShape; var Ind: Byte);
      procedure UpDateOneIndicator(Ind: TShape; Flag: Byte);
      begin
         with Ind do
          begin
             If Boolean(Flag) then
                Brush.Color := clYellow
             else
                Brush.Color := clGray
          end
      end;
   begin
      Ind := Indicator; {Присваиваем индикатору новое значение и пoказываем его}
      UpDateOneIndicator(Ind0, Indicator and   1);
      UpDateOneIndicator(Ind1, Indicator and   2);
      UpDateOneIndicator(Ind2, Indicator and   4);
      UpDateOneIndicator(Ind3, Indicator and   8);
      UpDateOneIndicator(Ind4, Indicator and  16);
      UpDateOneIndicator(Ind5, Indicator and  32);
      UpDateOneIndicator(Ind6, Indicator and  64);
      UpDateOneIndicator(Ind7, Indicator and 128)
   end;
begin
   case IndID of
    indDD: begin
              UpDate8Indicators(DD0,DD1,DD2,DD3,DD4,DD5,DD6,DD7, DD);
              OutDigitToDRG(8, Indicator shr 4);
              OutDigitToDRG(7, Indicator and $0F)
           end;
    indDA: begin
              UpDate8Indicators(DA0,DA1,DA2,DA3,DA4,DA5,DA6,DA7, DA);
              OutDigitToDRG(6, Indicator shr 4);
              OutDigitToDRG(5, Indicator and $0F)
           end;
    indDM: UpDate8Indicators(DM0,DM1,DM2,DM3,DM4,DM5,DM6,DM7, DM);
   indDRG: UpDate8Indicators(DRG0,DRG1,DRG2,DRG3,DRG4,DRG5,DRG6,DRG7, DRG);
   indTRG: UpDate8Indicators(TR0,TR1,TR2,TR3,TR4,TR5,TR6,TR7, tmp)
   end
end;

procedure TForm1.TurnDRG(const drgNum: Byte; const NewValue: Byte);
   procedure UpdateDRG(drg1,drg2,drg3,drg4,drg5,drg6,drg7,drg8: TShape);
      procedure ChangeDRG(const Condition: Boolean; drg: TShape);
      const
         colorON  = clTeal{Black};
         colorOFF = clSilver;
         colorPen = clGray;
      begin
         if Condition then
          with drg do
           begin
              Brush.Color := colorON;
                Pen.Color := colorON
           end
         else
          with drg do
           begin
              Brush.Color := colorOFF;
                Pen.Color := colorPen
           end
      end;

   begin
      ChangeDRG(NewValue and $01 = $01, drg1);
      ChangeDRG(NewValue and $40 = $40, drg2);
      ChangeDRG(NewValue and $08 = $08, drg3);
      ChangeDRG(NewValue and $20 = $20, drg4);
      ChangeDRG(NewValue and $02 = $02, drg5);
      ChangeDRG(NewValue and $10 = $10, drg6);
      ChangeDRG(NewValue and $04 = $04, drg7);
      ChangeDRG(NewValue and $80 = $80, drg8)
   end;
begin
   case drgNum of
     1:UpdateDRG(drgDL25,drgDL26,drgDL27,drgDL28,drgDL29,drgDL30,drgDL31,drgDL32);
     2:UpdateDRG(drgDL17,drgDL18,drgDL19,drgDL20,drgDL21,drgDL22,drgDL23,drgDL24);
     3:UpdateDRG(drgDL9,drgDL10,drgDL11,drgDL12,drgDL13,drgDL14,drgDL15,drgDL16);
     4:UpdateDRG(drgDL1,drgDL2,drgDL3,drgDL4,drgDL5,drgDL6,drgDL7,drgDL8);
     5:UpdateDRG(drgDA9,drgDA10,drgDA11,drgDA12,drgDA13,drgDA14,drgDA15,drgDA16);
     6:UpdateDRG(drgDA1,drgDA2,drgDA3,drgDA4,drgDA5,drgDA6,drgDA7,drgDA8);
     7:UpdateDRG(drgDD9,drgDD10,drgDD11,drgDD12,drgDD13,drgDD14,drgDD15,drgDD16);
     8:UpdateDRG(drgDD1,drgDD2,drgDD3,drgDD4,drgDD5,drgDD6,drgDD7,drgDD8)
   end
end;

procedure TForm1.OutDigitToDRG(const drgNum: Byte; const Digit: Byte);
begin
   case Digit of
     0: TurnDRG(drgNum, $3F);
     1: TurnDRG(drgNum, $06);
     2: TurnDRG(drgNum, $5B);
     3: TurnDRG(drgNum, $4F);
     4: TurnDRG(drgNum, $66);
     5: TurnDRG(drgNum, $6D);
     6: TurnDRG(drgNum, $7D);
     7: TurnDRG(drgNum, $07);
     8: TurnDRG(drgNum, $7F);
     9: TurnDRG(drgNum, $6F);
    10: TurnDRG(drgNum, $77);
    11: TurnDRG(drgNum, $7C);
    12: TurnDRG(drgNum, $39);
    13: TurnDRG(drgNum, $5E);
    14: TurnDRG(drgNum, $79);
    15: TurnDRG(drgNum, $71)
   end
end;

function TForm1.GetRegister(Index: Byte): Byte;
begin
   if Index in [0..7] then
     GetRegister := MPMemory[$F7+Index]
   else
     GetRegister := 0
end;

procedure Byte2Hex(const Value: Byte; var SymHi, SymLo: Char);
const
   HexSymbols = '0123456789ABCDEF';
begin
   SymHi := HexSymbols[Value div 16+1];
   SymLo := HexSymbols[Value-16*(Value div 16)+1]
end;

procedure TForm1.SetRegister(Index: Byte; NewValue: Byte);
var
   tmp: String[2];
begin
   if Index in [0..7] then
     begin
        MPMemory[$F7+Index] := NewValue;
        Byte2Hex(NewValue, Char(tmp[1]), Char(tmp[2]));
        tmp[0] := Char(2);
        case Index of
          0 : Reg0.Caption := tmp;
          1 : Reg1.Caption := tmp;
          2 : Reg2.Caption := tmp;
          3 : Reg3.Caption := tmp;
          4 : Reg4.Caption := tmp;
          5 : Reg5.Caption := tmp;
          6 : Reg6.Caption := tmp;
          7 : Reg7.Caption := tmp
        end
     end
end;

procedure TForm1.SetAccumulator(const NewValue: Byte);
var
   tmp: String[2];
begin
   MPMemory[$FF] := NewValue;
   Byte2Hex(NewValue, Char(tmp[1]), Char(tmp[2]));
   tmp[0]        := Char(2);
   Accum.Caption := tmp
end;

function TForm1.GetTRG: Byte;
begin
   GetTRG := Byte(TRG0.Checked)        +
             Byte(TRG1.Checked) shl 1  +
             Byte(TRG2.Checked) shl 2  +
             Byte(TRG3.Checked) shl 3  +
             Byte(TRG4.Checked) shl 4  +
             Byte(TRG5.Checked) shl 5  +
             Byte(TRG6.Checked) shl 6  +
             Byte(TRG7.Checked) shl 7;
end;

procedure TForm1.ExitButtonClick(Sender: TObject);
begin
   Close
end;

procedure TForm1.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin
    if MessageDlg('Завершить работу программы?',
                  mtConfirmation,[mbYes,mbNo], 0) = mrYes then
       CanClose := True
    else
       CanClose := False
end;

procedure TForm1.KeyResetClick(Sender: TObject);
var
   i: Byte;
begin
   for i := 0 to 7 do   {обнуляем содержимое}
     R[i] := 0;         {регистров,}
   AC := 0;             {аккумулятора и}
   SP := 0;             {указателя стека.}
   {сбрасываем в "0" все индикаторы}
   SetAndTurnIndicator(indDD, $00);   SetAndTurnIndicator(indDA,  $00);
   SetAndTurnIndicator(indDM, $00);   SetAndTurnIndicator(indDRG, $00);
   SetAndTurnIndicator(indTRG, TRG);
   Waiting4Num   := 0;
   ReadingNewAddr:= False;
   InputBuffer   := '$00';

   Out2SoundCard($0D, $32);
   Out2SoundCard($1B, $32);
   Out2SoundCard($2B, $32);
   Out2SoundCard($34, $32);
   Out2SoundCard($3F, $32);
   Out2SoundCard($4A, $32);
   Out2SoundCard($54, $32);
   Out2SoundCard($5E, $32);
   Out2SoundCard($67, $32);
   Out2SoundCard($6F, $32);
   Out2SoundCard($77, $32);
   Out2SoundCard($7F, $32)
end;

procedure TForm1.KeyRunCycleClick(Sender: TObject);
begin
   RunProgramm(True) {- выполняем всю программу}
end;

procedure TForm1.KeyRunStepClick(Sender: TObject);
begin
   RunProgramm(False) {- выполняем только текущую команду}
end;

procedure TForm1.KeySetAddrClick(Sender: TObject);
begin
   Out2SoundCard($0D, $19);
   ReadingNewAddr := True;
   Waiting4Num    := 2
end;

procedure TForm1.KeyLoadAccClick(Sender: TObject);
begin
   Out2SoundCard($0D, $19);
   ReadingNewAcc := True;
   Waiting4Num   := 2
end;

procedure TForm1.Digit_0Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('0')
end;

procedure TForm1.Digit_1Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
     InputDigit('1')
end;

procedure TForm1.Digit_2Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('2')
end;

procedure TForm1.Digit_3Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('3')
end;

procedure TForm1.Digit_4Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('4')
end;

procedure TForm1.Digit_5Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('5')
end;

procedure TForm1.Digit_6Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('6')
end;

procedure TForm1.Digit_7Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('7')
end;

procedure TForm1.Digit_8Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('8')
end;

procedure TForm1.Digit_9Click(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('9')
end;

procedure TForm1.Digit_AClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('A')
end;

procedure TForm1.Digit_BClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('B')
end;

procedure TForm1.Digit_CClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('C')
end;

procedure TForm1.Digit_DClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('D')
end;

procedure TForm1.Digit_EClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('E')
end;

procedure TForm1.Digit_FClick(Sender: TObject);
begin
   if Waiting4Num = 0 then
     Waiting4Num := 2;
   InputDigit('F')
end;

procedure TForm1.InputDigit(const Digit: Char);
var
   InputNumber: Byte;
   ErrorCode  : Integer;
begin
   if Waiting4Num > 0 then
     begin
        InputBuffer[4-Waiting4Num] := Digit;
        Dec(Waiting4Num);
        if Waiting4Num = 0 then
          begin
             Out2SoundCard($7F, $19);
             Val(InputBuffer, InputNumber, ErrorCode);
             if ErrorCode = 0 then
               begin
                  if ReadingNewAddr then
                    begin
                       {Устан-ем и показываем новое значение текущего адреса}
                       SetAndTurnIndicator(indDA, InputNumber);
                       {и показываем содержимое памяти по введённому адресу}
                       SetAndTurnIndicator(indDM, MPMemory[DA]);
                       ReadingNewAddr := False
                    end
                  else
                   if ReadingNewAcc then
                     begin
                        AC            := InputNumber;
                        SetAndTurnIndicator(indDD, AC);
                        ReadingNewAcc := False
                     end
                   else
                    begin
                       {Заносим в память по текущему адресу введённое число
                        и показываем его на индикаторе DA}
                       if DA in [$F7..$FE] then
                         R[DA-$F7] := InputNumber
                       else
                        if DA = $FF then
                          AC := InputNumber
                        else
                          MPMemory[DA] := InputNumber;
                       SetAndTurnIndicator(indDD, MPMemory[DA])
                    end
               end
          end
        else
          Out2SoundCard($0D, $19)
     end
end;

procedure TForm1.KeyWriteIncClick(Sender: TObject);
begin
   ReadingNewAddr := False;   Waiting4Num := 0;
   if DA < $FF then
     begin
        Inc(DA);
        SetAndTurnIndicator(indDA, DA)
     end;
   SetAndTurnIndicator(indDM, MPMemory[DA]);
   Out2SoundCard($0D, $19)
end;

procedure TForm1.KeyReadIncClick(Sender: TObject);
begin
   ReadingNewAddr := False;   Waiting4Num := 0;
   if DA < $FF then
     begin
        Inc(DA);
        SetAndTurnIndicator(indDA, DA)
     end;
   SetAndTurnIndicator(indDM, MPMemory[DA]);
   Out2SoundCard($0D, $19)
end;

procedure TForm1.KeyReadDecClick(Sender: TObject);
begin
   ReadingNewAddr := False;   Waiting4Num := 0;
   if DA > 0 then
     begin
        Dec(DA);
        SetAndTurnIndicator(indDA, DA)
     end;
   SetAndTurnIndicator(indDM, MPMemory[DA]);
   Out2SoundCard($0D, $19)
end;

procedure TForm1.RunProgramm(const RunMode: Boolean);
var
   CurByte: Byte;
   tmpWord: Word;

   procedure DoComamndALRn(const RegN: Byte);
   begin
      tmpWord := R[RegN] + AC;
      AC := R[RegN] + AC;
      if tmpWord <= $FF then
        DA := MPMemory[DA+1]-1
      else
        DA := MPMemory[DA+2]-1
   end;

begin
   While (DA <= $FF)and(MPMemory[DA] <> $A0) do{A0h-команда завершения программы}
     begin
        CurByte := MPMemory[DA];
        case CurByte of
        {0 -> Rn}
           $B7..$BE: R[CurByte-$B7] := 0;
        {0 -> AC}
           $BF     : AC := 0;
        {Rn+1 -> Rn}
           $C7..$CE: R[CurByte-$C7] := R[CurByte-$C7] + 1;
        {AC+1 -> AC}
           $CF     : AC := AC + 1;
        {Rn-1 -> Rn}
           $66..$6D: R[CurByte-$66] := R[CurByte-$66] - 1;
        {AC-1 -> AC}
           $6E     : AC := AC - 1;
        {Rn -> AC}
           $A8..$AF: AC := R[CurByte-$A8];
        {AC -> Rn}
           $5A..$61: R[CurByte-$5A] := AC;
        {Mn -> AC
         n       }
           $9E     : begin
                        AC := MPMemory[MPMemory[DA+1]];
                        Inc(DA)
                     end;
        {not AC -> AC}
           $72     : AC := $FF - AC;
        {AC -> Mn
         n       }
           $9F     : begin
                        MPMemory[MPMemory[DA+1]] := AC;
                        Inc(DA)
                     end;
        {0FFh -> AC}
           $73     : AC := $FF;
        {Rn+AC -> Rn,AC}
           $77..$7E: begin
                        R[CurByte-$77] := R[CurByte-$77] + AC;
                        AC             := R[CurByte-$77]
                     end;
        {AC+AC -> AC}
           $7F     : AC := AC + AC;
        {Rn -> RA, AC -> M}
           $95..$9C: MPMemory[R[CurByte-$95]] := AC;
        {AC -> RA, AC -> M}
           $9D     : MPMemory[AC] := AC;
        {Rn -> RA, M -> AC}
           $E7..$EE: AC := MPMemory[R[CurByte-$E7]];
        {AC -> RA, M -> AC}
           $EF     : AC := MPMemory[R[CurByte-$EF]];
        {TZRn
         a1
         a2  }
           $8A..$91: if R[CurByte-$8A] = $00 then
                        DA := MPMemory[DA+1]-1      {вычитается единица,}
                     else                           {т.к. в конце цикла}
                        DA := MPMemory[DA+2]-1;     {идёт увеличение на 1}
        {TZA}
           $92     : if AC = $00 then
                        DA := MPMemory[DA+1]-1      {вычитается единица,}
                     else                           {т.к. в конце цикла}
                        DA := MPMemory[DA+2]-1;     {идёт увеличение на 1}
        {ALRn (Rn+AC -> AC)
         a1
         a2  }
           $20     : DoComamndALRn(0);
           $30     : DoComamndALRn(1);
           $33     : DoComamndALRn(2);
           $36     : DoComamndALRn(3);
           $39     : DoComamndALRn(4);
           $3F     : DoComamndALRn(5);
           $44     : DoComamndALRn(6);
           $49     : DoComamndALRn(7);
        {ALA (AC+AC -> AC)
         a1
         a2  }
           $4C     : begin
                        tmpWord := AC + AC;
                        AC := AC + AC;
                        if tmpWord <= $FF then
                          DA := MPMemory[DA+1]-1
                        else
                          DA := MPMemory[DA+2]-1
                     end;
        {GOTOn
         n   }
           $0A     : DA := MPMemory[DA+1] - 1;
        {SRA0, SRA1}
           $93,$16 : begin
                        AC := AC shr 1;
                        if CurByte = $16 then
                          AC := AC or $80;
                        if (AC and $01) = 0 then
                          DA := MPMemory[DA+1]-1
                        else
                          DA := MPMemory[DA+2]-1
                     end;
        {CALLn
         n   }
           $65     : begin
                        SP := SP+1;
                        MPMemory[addrSP-SP] := DA+2;     {- заносим в стек
                                                            адрес возврата}
                        DA := MPMemory[DA+1]-1 {и переходим к подпрограмме}
                     end;
        {RET}
           $A7     : begin
                        DA := MPMemory[addrSP-SP]-1;      {возвращаемся из}
                        SP := SP-1                           {подпрограммы}
                     end;
        {INn
         n   }
           $4E     : begin
                        case MPMemory[DA+1] of
                         $00: AC := 0;
                         $01: AC := 0;
                         $02: AC := 0;
                         $03: AC := 0;
                         $04: AC := 0;
                         $05: AC := 0;
                         $06: AC := 0;
                         $07: AC := TRG;
                         $08: AC := 0
                        end;
                        Inc(DA)
                     end;
        {OUTn
         n   }
           $A4     : begin
                        case MPMemory[DA+1] of
                         $00     : ;
                         $01..$04: TurnDRG(MPMemory[DA+1], AC);
                         $05     : ;
                         $06     : ;
                         $07     : ;
                         $08     : SetAndTurnIndicator(indDRG, AC)
                        end;
                        Inc(DA)
                     end;
        {MSC
         FREQ
         TIME
         ...
         $00 }
           $75     : begin
                        Inc(DA);
                        while (MPMemory[DA] > $00) and (DA < $FF) do
                          begin
                             Out2SoundCard(MPMemory[DA], MPMemory[DA+1]);
                             Inc(DA, 2)
                          end
                     end;
        {IFS
         a1
         a2  }
           $94     : begin
                        DA := MPMemory[DA+1]-1
                     end;
        {IFFn
         n   }
           $54     : begin
                        Inc(DA)
                     end
        else
          begin
             MessageDlg(Format('По адресу %xh неверно введена команда', [DA]),mtError,[mbOk],0);
             Break
          end
        end;  {end of case}
        if DA < $FF then
          begin
             Inc(DA);
             SetAndTurnIndicator(indDA, DA)
          end;
        SetAndTurnIndicator(indDM, MPMemory[DA]);
        if not RunMode then     {если выполнить только текущую команду,}
          Break                 {то выход}
     end
end;

procedure TForm1.TRG0Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG1Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG2Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG3Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG4Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG5Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.TRG6Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG);
end;

procedure TForm1.TRG7Click(Sender: TObject);
begin
   SetAndTurnIndicator(indTRG, TRG)
end;

procedure TForm1.FullResetClick(Sender: TObject);
var
   i: Byte;
begin
   {Обнуляем всю память}
   for i := $00 to $FF do
     MPMemory[i] := 0;
   {сбрасываем в "0" все индикаторы}
   SetAndTurnIndicator(indDD, $00);   SetAndTurnIndicator(indDA,  $00);
   SetAndTurnIndicator(indDM, $00);   SetAndTurnIndicator(indDRG, $00);
   SetAndTurnIndicator(indTRG, TRG);
   Waiting4Num   := 0;
   ReadingNewAddr:= False;
   InputBuffer   := '$00';
   TurnDRG($01, 0);   TurnDRG($02, 0);
   TurnDRG($03, 0);   TurnDRG($04, 0)
end;

procedure TForm1.AboutButtonClick(Sender: TObject);
begin
   ShowMessage('             Имитатор учебной ЭВМ МП-589'#13#13+
               '                        Разработал Плющ З.Е.'#13+
               'МГТУ им.Н.Э.Баумана, кафедра "АСОИиУ"'#13+
               '                            Москва, 1998 год')
end;

end.
