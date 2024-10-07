package code.model;

import code.main.GameCanvas;
import lib.mGraphics;
import lib2.mFont;

public class RunWord {
    public boolean showDlg = false;
    public int nStepToShow;
    public int currentDlgStep;
    public int dlgActionType;
    public String[] dlgChainInfo;
    public int dlgFocusX;
    public int dlgFocusY;
    public int dlgRunX;
    public int dlgRunY;
    public int dlgW;
    public int dlgX;
    mFont f;

    public void startDialogChain(String dlgChain, int ActionType, int xFocus, int yFocus, int w, mFont f) {
        this.f = f;
        this.dlgActionType = ActionType;
        this.dlgFocusX = xFocus;
        this.dlgFocusY = yFocus;
        this.currentDlgStep = 0;
        this.dlgW = w;
        if (this.dlgW > GameCanvas.w - 10) {
            this.dlgW = GameCanvas.w - 10;
        }

        this.dlgChainInfo = f.splitFontArray(dlgChain, this.dlgW);
        this.dlgX = this.dlgFocusX;
        this.showDlg = true;
        this.dlgRunX = 0;
        this.dlgRunY = 0;
    }

    public boolean checkDlgStep() {
        return this.dlgRunY >= this.dlgChainInfo.length;
    }

    public boolean nextDlgStep() {
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunY = this.dlgChainInfo.length;
            this.dlgRunX = 0;
            return false;
        } else {
            this.dlgRunX = this.dlgRunY = 0;
            return true;
        }
    }

    public void updateDlg() {
        if (this.showDlg && this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunX += 2;
            if (this.dlgRunX >= this.dlgChainInfo[this.dlgRunY].length()) {
                this.dlgRunX = 0;
                ++this.dlgRunY;
            }
        }

    }

    public void paintText(mGraphics g) {
        int dlgTextY = -1;
        dlgTextY = this.dlgFocusY;
        for (int i = 0; i < this.dlgRunY; i++)
            this.f.drawString(g, this.dlgChainInfo[i], this.dlgX, dlgTextY + i *
                    GameCanvas.hText, 0, false);
        if (this.dlgRunY < this.dlgChainInfo.length)
            this.f.drawString(g, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX,
                    dlgTextY + this.dlgRunY * (GameCanvas.hText - 5), 0, false);
    }

    public void paintText(mGraphics g, int archor) {
        int dlgTextY = -1;
        dlgTextY = this.dlgFocusY;
        if (archor == 2) {
            for (int i = 0; i < this.dlgRunY; i++)
                this.f.drawString(g, this.dlgChainInfo[i], this.dlgX + this.dlgW / 2, dlgTextY + i * (
                        GameCanvas.hText - 5), 2, false);
            if (this.dlgRunY < this.dlgChainInfo.length)
                this.f.drawString(g, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX),
                        this.dlgX + this.dlgW / 2, dlgTextY + this.dlgRunY * (GameCanvas.hText - 5), 2, false);
        } else {
            for (int i = 0; i < this.dlgRunY; i++)
                this.f.drawString(g, this.dlgChainInfo[i], this.dlgX, dlgTextY + i * (
                        GameCanvas.hText - 5), 0, false);
            if (this.dlgRunY < this.dlgChainInfo.length)
                this.f.drawString(g, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX),
                        this.dlgX, dlgTextY + this.dlgRunY * (GameCanvas.hText - 5), 0, false);
        }
    }
}
