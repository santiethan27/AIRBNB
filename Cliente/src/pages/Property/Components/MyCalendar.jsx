import React, { useEffect, useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { useReserve } from '../../../context/ReserveContext';

const MyCalendar = ({ setDates, datesReserve, propertyId }) => {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [invalidRange, setInvalidRange] = useState(false);
    const { reserves, _getReserve } = useReserve();
    useEffect(() => {
        const getData = async () => {
            await _getReserve(propertyId);
        }
        getData();
    }, [])

    const handleStartDateChange = (date) => {
        setStartDate(date);
        if (endDate && date > endDate) {
            setEndDate(null);
        }
        setInvalidRange(false);
    };
    const handleEndDateChange = (date) => {
        setEndDate(date);
        if (!startDate) {
            setEndDate(null);
            return;
        }
        if (startDate && date < startDate) {
            setStartDate(null);
        }
        setInvalidRange(false);
    };

    useEffect(() => {
        if (startDate && endDate) {
            handleReservation();
        }
    }, [endDate]);

    const handleReservation = () => {
        console.log(startDate, endDate)
        let totalDays = 0;
        const isRangeReserved = reserves.some((reservedDate) => {
            const currentDate = new Date(reservedDate.startDate + 'T00:00:00');
            const finDate = new Date(reservedDate.endDate + 'T23:59:59');
            return (
                (startDate <= currentDate && endDate >= currentDate) ||
                (startDate <= finDate && endDate >= finDate) ||
                (startDate >= currentDate && endDate <= finDate)
            );
        });

        const diffTime = Math.abs(endDate - startDate);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
        totalDays = diffDays;
        if (isRangeReserved) {
            setInvalidRange(true);
            setStartDate(null);
            setEndDate(null);
            return;
        }

        setDates([{ startDate, endDate, totalDays }]);
        setStartDate(null);
        setEndDate(null);
    };

    const disabledDates = reserves?.map((reservedDate) => {
        const dates = [];
        const currentDate = new Date(reservedDate.startDate + 'T00:00:00');
        const finDate = new Date(reservedDate.endDate + 'T23:59:59');
        while (currentDate <= finDate) {
            dates.push(new Date(currentDate));
            currentDate.setDate(currentDate.getDate() + 1);
        }
        return dates;
    }).flat();

    return (
        <div>
            <h2>Selecciona un rango de fechas:</h2>
            <DatePicker
                selected={startDate}
                onChange={handleStartDateChange}
                selectsStart
                startDate={startDate}
                endDate={endDate}
                minDate={new Date()}
                excludeDates={disabledDates}
                placeholderText="Fecha de inicio"
            />
            <DatePicker
                selected={endDate}
                onChange={handleEndDateChange}
                selectsEnd
                startDate={startDate}
                endDate={endDate}
                minDate={startDate}
                excludeDates={disabledDates}
                placeholderText="Fecha de fin"
            />
            {invalidRange && <p style={{ color: 'red' }}>El rango seleccionado no está disponible, por favor selecciona otro.</p>}
            {
                datesReserve?.map((reservedDate, index) => (
                    <div key={index}>
                        <span>Reservado: {reservedDate.startDate.toLocaleDateString()} - {reservedDate.endDate.toLocaleDateString()}</span>
                    </div>
                ))

            }
        </div>
    );
};

export default MyCalendar;