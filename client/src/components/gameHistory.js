import './gameHistory.scss';
import React, {useState} from 'react';

export const GameHistory = ({ gameHistory }) => {
    const [tableOpen, setTableOpen] = useState(false);
    const { history } = gameHistory;

    let gameTable = history;
    if (!tableOpen && history.length) {
        gameTable = [history[history.length - 1]];
    }

    const classes = `game-history ${tableOpen ? 'game-history_open' : null}`;

    const choiceConverter = (choice) => {
        switch(choice) {
            case 'R': return 'Rock';
            case 'S': return 'Scissors';
            case 'P': return 'Paper';
            default: return '';
        }
    };

    return <div className={classes}>
        <table className='game-history__table'>
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Skynet</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
                {gameTable.map((it, index) => <tr key={index}>
                    <td>{choiceConverter(it.playerChoice)}</td>
                    <td>{choiceConverter(it.skynetChoice)}</td>
                    <td>{it.outcome.toLowerCase()}</td>
                </tr>
                )}
            </tbody>
        </table>
        <button className='game-history__table-open' onClick={() => setTableOpen((tableOpen) => !tableOpen)}/>
    </div>
};
